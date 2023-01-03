package App.serviceImpl;

import App.Error;
import App.entity.Person;
import App.entity.Product;
import App.entity.Statement;
import App.exception.InvestorOrProductNotFoundException;
import App.exception.WithdrawalException;
import App.repository.PersonRepository;
import App.repository.ProductRepository;
import App.repository.StatementRepository;
import App.service.StatementService;
import App.service.event.KafkaProducerService;
import App.vo.WithdrawVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class StatementServiceImpl implements StatementService {
    public static final int MIN_RETIREMNT_AGE = 65;
    private final StatementRepository statementRepository;
    private final PersonRepository investorRepository;
    private final PersonRepository personRepository;

    private final ProductRepository productRepository;
    @Autowired
    private final KafkaProducerService kafkaProducer;

    @Override
    public Statement save(Statement withdrawal) {
        return statementRepository.save(withdrawal);
    }

    @Override
    public Statement findByInvestorIdAndProductId(String investorId, String productId) {
        return statementRepository.findByInvestorIdAndProductId(investorId, productId).orElseThrow(InvestorOrProductNotFoundException::new);
    }

    @Override
    public Statement updateWithdrawal(Statement withdrawal, Integer withdrawalId) {
        Statement withdrawal1 = findById(withdrawalId);

        return save(withdrawal1);
    }

    @Override
    public List<Statement> findAll() {
        return statementRepository.findAll();
    }

    /***
     *
     * @param investorId
     * @param productId
     * @param withdrawalAmount
     * @return
     */
    @Override
    public Statement submitWithdrawal(String investorId, String productId, BigDecimal withdrawalAmount) {
        WithdrawVO withdrawalVO = getWithdrawalVO(investorId, productId, withdrawalAmount);
        Error error = validateAndWithdraw(withdrawalVO.getCurrentBalance(), withdrawalAmount, withdrawalVO.getProductType(), withdrawalVO.getAge());
        Statement statement = new Statement();
        if (error.isEmpty()) {
            statement.setInvestorId(investorId);
            statement.setProductId(productId);
            statement.setBalance(withdrawalVO.getCurrentBalance());
            statement.setWithdrawalAmount(withdrawalAmount);
            statement.setStatus("STARTED");
            this.kafkaProducer.sendMessage(withdrawalVO);
            return save(statement);
        } else {
            log.error(error.getMessage());
            log.warn("validation failed...");
            throw new WithdrawalException(error);
        }
    }

    @Override
    public Statement findById(Integer id) {
        return statementRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Withdrawal Not Found"));
    }

    @Override
    public Statement update(WithdrawVO vo) {
        return null;
    }

    private WithdrawVO getWithdrawalVO(String investorId, String productId, BigDecimal withdrawalAmount) {
        WithdrawVO vo = new WithdrawVO();
        try{
            Optional<Statement> investorStatement =  statementRepository.findByInvestorIdAndProductId(investorId,productId);

            if (investorStatement.isPresent()) {
                Person person = personRepository.findById(Integer.valueOf(investorId)).get();
                Product product = productRepository.findById(Integer.valueOf(productId)).get();
                vo.setInvestorId(investorId);
                vo.setProductId(productId);
                vo.setCurrentBalance(investorStatement.get().getBalance());
                vo.setProductType(product.getType());
                vo.setWithdrawalAmount(withdrawalAmount);
                vo.setAge(person.getAge());
                vo.setStatus("STARTED");
                return vo;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //TODO
       throw new WithdrawalException(null);
    }

    /***
     * validation: withdrawalAmount should not be above current balance,
     * investor cannot withdraw more than 90% of the current balance,
     * if product type is retirement, the investor's age must be > 65 years
     * @param currentBalance
     * @param withdrawalAmount
     * @param productType
     */
    private Error validateAndWithdraw(BigDecimal currentBalance, BigDecimal withdrawalAmount, String productType, int age) {
        Error error = new Error();
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
       // BigDecimal subtract = withdrawalAmount.subtract(currentBalance, mc);
       /* if (withdrawalAmount.compareTo(currentBalance) == 1) {
            log.warn("withdrawal amount cannot be greater than current balance");
            error.setCode("E1");
            error.setMessage("withdrawal amount cannot be greater than current balance");
        } else*/ if ("retirement".equals(productType) & age < MIN_RETIREMNT_AGE) {
            log.warn("investor age doe not meet the minimum age restriction for the product");
            error.setCode("E2");
            error.setMessage("withdrawal amount cannot be greater than current balance");
        }
        return error;
    }

    /***
     * this method is triggered by an event when investment withdrawal get submitted
     * retrieve the withdrawal transaction
     * subtract the requested withdrawal amount from the current balance
     * update the status
     * @param vo
     */
    private void withdrawFromInvestmentAccount(WithdrawVO vo){
         //find the product by Id, then update the balance - Product
        //update the status from Withdrawal table by withdrawal id - Withdrawal
       // withdrawalRepository.findById(vo.get)
        //personRepository.findById(vo.getProductId());

    }
}
