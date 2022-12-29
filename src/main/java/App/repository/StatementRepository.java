package App.repository;

import App.entity.Statement;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatementRepository extends ListCrudRepository<Statement, Integer> {
    Optional<Statement> findByInvestorIdAndProductId(String investorId, String productId);
}
