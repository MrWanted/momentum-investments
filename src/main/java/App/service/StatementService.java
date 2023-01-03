package App.service;

import App.entity.Statement;
import App.vo.WithdrawVO;

import java.math.BigDecimal;
import java.util.List;

public interface StatementService {
    Statement save(Statement withdrawal);

    Statement findByInvestorIdAndProductId(String investorId, String productId);
    Statement updateWithdrawal(Statement withdrawal, Integer withdrawalId);
    List<Statement> findAll();
    Statement submitWithdrawal(String investorId, String productId, BigDecimal withdrawalAmount);
    Statement findById(Integer id);
    Statement update(WithdrawVO withdrawal);
}
