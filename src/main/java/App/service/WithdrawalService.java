package App.service;

import App.entity.Withdrawal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface WithdrawalService {
    Withdrawal save(Withdrawal withdrawal);
    List<Withdrawal> findAll();
    void withdraw(Integer investorId, Integer productId, BigDecimal amount);
}
