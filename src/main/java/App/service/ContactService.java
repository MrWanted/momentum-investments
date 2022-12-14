package App.service;

import App.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact save(Contact address);
    List<Contact> findAll();
    Optional<Contact> findById(Integer id);
}
