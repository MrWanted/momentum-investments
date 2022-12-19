package App.repository;

import App.entity.BankDetails;
import org.springframework.data.repository.ListCrudRepository;

public interface BankDetailsRepository extends ListCrudRepository<BankDetails, Integer> {
}
