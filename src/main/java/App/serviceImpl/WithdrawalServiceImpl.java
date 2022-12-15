package App.serviceImpl;

import App.entity.Person;
import App.entity.Product;
import App.entity.Withdrawal;
import App.repository.PersonRepository;
import App.repository.WithdrawalRepository;
import App.service.WithdrawalService;
import App.vo.WithdrawVO;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class WithdrawalServiceImpl implements WithdrawalService {
    private final WithdrawalRepository withdrawalRepository;
    private final PersonRepository investorRepository;
    @Override
    public Withdrawal save(Withdrawal withdrawal) {
        return withdrawalRepository.save(withdrawal);
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
     */
    @Override
    public void withdraw(Integer investorId, Integer productId, BigDecimal amount) {

    }

    private WithdrawVO getWithdrwalaVO(Integer investorId, Integer productId, BigDecimal withdrawalAmount) {
        WithdrawVO vo = new WithdrawVO();
        Optional<Person> investor = investorRepository.findById(investorId);
        if(investor.isPresent()){
            Person person = investor.get();
            String age = person.getAge();
            List<Product> products = person.getProducts();
            BigDecimal balance = BigDecimal.ZERO;
            String productType = null;
            for(Product product:products){
                if(product.getId() == productId){
                    balance = product.getBalance();
                    productType =  product.getType();
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
    private boolean validateAndWithdraw(BigDecimal currentBalance, BigDecimal withdrawalAmount, String productType, String age){


        return false;
    }
}
