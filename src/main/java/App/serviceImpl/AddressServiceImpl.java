package App.serviceImpl;

import App.entity.Address;
import App.repository.AddressRepository;
import App.service.AddressService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    @Override
    public Address save(Address address) {
        return repository.save(address);
    }
    @Override
    public List<Address> findAll() {
        return repository.findAll();
    }
    @Override
    public Optional<Address> findById(Integer id) {
        return repository.findById(id);
    }
}
