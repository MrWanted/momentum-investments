package App.serviceImpl;

import App.entity.Withdrawal;
import App.repository.WithdrawalRepository;
import App.service.WithdrawalService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
@Data
public class WithdrawalServiceImpl implements WithdrawalService {
    private final WithdrawalRepository withdrawalRepository;
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
     * validation: withdrawalAmount should not be above current balance,
     * investor cannot withdraw more than 90% of the current balance,
     * if product type is retirement, the investor's age must be > 65 years
     */
    @Override
    public void withdraw(String investorId, String productId, BigDecimal amount) {

    }
}
