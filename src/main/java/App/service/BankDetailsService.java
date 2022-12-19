package App.service;

import App.entity.BankDetails;
import java.util.List;
import java.util.Optional;

public interface BankDetailsService {
    BankDetails save(BankDetails address);
    List<BankDetails> findAll();
    BankDetails findById(Integer id);
}
