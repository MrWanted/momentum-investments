package App.service;

import App.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address save(Address address);
    List<Address> findAll();
    Optional<Address> findById(Integer id);
}
