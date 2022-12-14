package App.repository;

import App.entity.Withdrawal;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends ListCrudRepository<Withdrawal, Integer> {
}
