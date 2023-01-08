package App.service;

import App.entity.BankDetails;
import java.util.List;

public interface BankDetailsService {
    List<BankDetails> findAll();
    BankDetails findById(Integer id);
}
