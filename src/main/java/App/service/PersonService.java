package App.service;

import App.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person save(Person product);
    List<Person> findAll();
    Optional<Person> findByID(Integer id);
    void deleteById(Integer id);
}
