package App.serviceImpl;

import App.Error;
import App.entity.Person;
import App.entity.Product;
import App.entity.Withdrawal;
import App.exception.WithdrawalException;
import App.repository.PersonRepository;
import App.repository.WithdrawalRepository;
import App.service.WithdrawalService;
import App.vo.WithdrawVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
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
public class WithdrawalServiceImpl implements WithdrawalService {
    public static final int MIN_RETIREMNT_AGE = 65;
    private final WithdrawalRepository withdrawalRepository;
    private final PersonRepository investorRepository;
    private final PersonRepository personRepository;
    @Autowired
    private KafkaTemplate<String, WithdrawVO> kafkaTemplate;

    @Override
    public Withdrawal save(Withdrawal withdrawal) {

        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal updateWithdrawal(Withdrawal withdrawal, Integer withdrawalId) {
        Withdrawal withdrawal1 = findById(withdrawalId);

        return save(withdrawal1);
    }

    @Override
    public List<Withdrawal> findAll() {
        return withdrawalRepository.findAll();
    }

    /***
     *
     * @param investorId
     * @param productId
     * @param amount
     * @return
     */
    @Override
    public Withdrawal submitWithdrawal(Integer investorId, Integer productId, BigDecimal amount) {
        WithdrawVO withdrawalVO = getWithdrawalVO(investorId, productId, amount);
        Error error = validateAndWithdraw(withdrawalVO.getCurrentBalance(), withdrawalVO.getWithdrawalAmount(), withdrawalVO.getProductType(), withdrawalVO.getAge());
        Withdrawal withdrawal = new Withdrawal();
        if (!error.isEmpty()) {
            withdrawal.setInvestorId(String.valueOf(investorId));
            withdrawal.setProductId(String.valueOf(productId));
            withdrawal.setWithdrawalAmount(amount);
            withdrawal.setStatus("STARTED");
            save(withdrawal);
            //trigger an event
            //this.kafkaTemplate.send("investor_withdrawals",withdrawalVO);
        } else {
            throw new WithdrawalException(error);
        }
        return withdrawal;
    }

    @Override
    public Withdrawal findById(Integer id) {
        return withdrawalRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Withdrawal Not Found"));
    }

    @Override
    public Withdrawal update(WithdrawVO vo) {
        return null;
    }

    private WithdrawVO getWithdrawalVO(Integer investorId, Integer productId, BigDecimal withdrawalAmount) {
        WithdrawVO vo = new WithdrawVO();
        Optional<Person> investor = investorRepository.findById(investorId);
        if (investor.isPresent()) {
            Person person = investor.get();
            int age = person.getAge();
            List<Product> products = null;//person.getProducts();
            BigDecimal balance = BigDecimal.ZERO;
            String productType = null;
            for (Product product : products) {
                if (product.getId() == productId) {
                    balance = product.getBalance();
                    productType = product.getType();
                }
            }
            vo.setInvestorId(investorId);
            vo.setProductId(productId);
            vo.setCurrentBalance(balance);
            vo.setProductType(productType);
            vo.setWithdrawalAmount(withdrawalAmount);
            vo.setAge(age);
            vo.setStatus("STARTED");
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
    private Error validateAndWithdraw(BigDecimal currentBalance, BigDecimal withdrawalAmount, String productType, int age) {
        Error error = new Error();
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        BigDecimal subtract = withdrawalAmount.subtract(currentBalance, mc);
        if (withdrawalAmount.compareTo(currentBalance) == 1) {
            log.warn("withdrawal amount cannot be greater than current balance");
            error.setCode("E1");
            error.setMessage("withdrawal amount cannot be greater than current balance");
        } else if ("retirement".equals(productType) & age < MIN_RETIREMNT_AGE) {
            log.warn("investor age doe not meet the minimum age restriction for the product");
            error.setCode("E2");
            error.setMessage("withdrawal amount cannot be greater than current balance");
        } else if (false) {
            error.setCode("E3");
            error.setMessage("withdrawal amount does not meet max withdrawal amount restrictions");
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
        personRepository.findById(vo.getProductId());

    }
}
