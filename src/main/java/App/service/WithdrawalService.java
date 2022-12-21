package App.service;

import App.entity.Withdrawal;
import App.vo.WithdrawVO;

import java.math.BigDecimal;
import java.util.List;

public interface WithdrawalService {
    Withdrawal save(Withdrawal withdrawal);

    Withdrawal updateWithdrawal(Withdrawal withdrawal, Integer withdrawalId);
    List<Withdrawal> findAll();
    Withdrawal submitWithdrawal(Integer investorId, Integer productId, BigDecimal amount);
    Withdrawal findById(Integer id);
    Withdrawal update(WithdrawVO withdrawal);
}
