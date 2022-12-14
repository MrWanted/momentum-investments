package App.serviceImpl;

import App.entity.BankDetails;
import App.repository.BankDetailsRepository;
import App.service.BankDetailsService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Data
@Service
public class BankDetailsServiceImpl implements BankDetailsService {
    private BankDetailsRepository repository;
    @Override
    public List<BankDetails> findAll() {
        return repository.findAll();
    }

    @Override
    public BankDetails findById(Integer id) {
        return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Bank details Not Found"));
    }
}
