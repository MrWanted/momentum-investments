package App.serviceImpl;

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
import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class StatementServiceImpl implements StatementService {
    public static final int MIN_RETIREMNT_AGE = 65;
    public static final String INVESTOR_WITHDRAWAL_AGE_DOES_RESTRICTION = "investor age does not meet the minimum age restriction for the product";
    public static final String INVESTOR_MAX_WITHDRAWAL_RESTRICTION = "investor cannot withdraw more than 90% of the current balance";
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
        try {
            WithdrawVO withdrawalVO = getWithdrawalVO(investorId, productId, withdrawalAmount);
            validate(withdrawalVO.getCurrentBalance(), withdrawalAmount, withdrawalVO.getProductType(), withdrawalVO.getAge());
            Statement statement = new Statement();
            statement.setInvestorId(investorId);
            statement.setProductId(productId);
            statement.setBalance(withdrawalVO.getCurrentBalance());
            statement.setWithdrawalAmount(withdrawalAmount);
            statement.setStatus("STARTED");
            statementRepository.save(statement);
            this.kafkaProducer.sendMessage(withdrawalVO);
            return statement;
        }
        catch (WithdrawalException we){
            throw new WithdrawalException(we.getMessage());
        }
        catch (InvestorOrProductNotFoundException ipe) {
            throw new InvestorOrProductNotFoundException();
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Statement findById(Integer id) {
        return statementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Withdrawal Not Found"));
    }

    private WithdrawVO getWithdrawalVO(String investorId, String productId, BigDecimal withdrawalAmount) {
        WithdrawVO vo = new WithdrawVO();
        try {
            Optional<Statement> investorStatement = statementRepository.findByInvestorIdAndProductId(investorId, productId);

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
            }
        } catch (InvestorOrProductNotFoundException e) {
            log.error("error getting the withdrawal vo", e);
           throw new InvestorOrProductNotFoundException();
        }
        catch (WithdrawalException e) {
            log.error("error getting the withdrawal vo", e);
            throw new WithdrawalException("");
        }
        return vo;
    }
    /***
     * validation: withdrawalAmount should not be above current balance,
     * investor cannot withdraw more than 90% of the current balance,
     * if product type is retirement, the investor's age must be > 65 years
     * @param currentBalance
     * @param withdrawalAmount
     * @param productType
     */
    private void validate(BigDecimal currentBalance, BigDecimal withdrawalAmount, String productType, int age) {
        if (withdrawalAmount.compareTo(currentBalance) == 1) {
            log.warn("withdrawal amount cannot be greater than current balance");
            throw new WithdrawalException("withdrawal amount cannot be greater than current balance");
        } else if (withdrawalAmount.compareTo(currentBalance.multiply(BigDecimal.valueOf(0.9))) == 1) {
            log.warn(INVESTOR_MAX_WITHDRAWAL_RESTRICTION);
            throw new WithdrawalException(INVESTOR_MAX_WITHDRAWAL_RESTRICTION);
        } else if ("RETIREMENT".equalsIgnoreCase(productType) & age < MIN_RETIREMNT_AGE) {
            log.warn(INVESTOR_WITHDRAWAL_AGE_DOES_RESTRICTION);
            throw new WithdrawalException(INVESTOR_WITHDRAWAL_AGE_DOES_RESTRICTION);
        }
    }
}
