package App.serviceImpl;

import App.entity.Contact;
import App.repository.ContactRepository;
import App.service.ContactService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    @Override
    public Contact save(Contact address) {
        return repository.save(address);
    }
    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }
    @Override
    public Optional<Contact> findById(Integer id) {
        return repository.findById(id);
    }
}
