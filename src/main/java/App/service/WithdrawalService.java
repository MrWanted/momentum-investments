package App.service;

import App.entity.Withdrawal;
import App.vo.WithdrawVO;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WithdrawalService {
    Withdrawal save(Withdrawal withdrawal);
    List<Withdrawal> findAll();
    Withdrawal submitWithdrawal(Integer investorId, Integer productId, BigDecimal amount);
    Optional<Withdrawal> findById(Integer id);
    Withdrawal update(WithdrawVO withdrawal);
}
