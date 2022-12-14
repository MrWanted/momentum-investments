package App.repository;

import App.entity.Contact;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends ListCrudRepository<Contact, Integer> {
}
