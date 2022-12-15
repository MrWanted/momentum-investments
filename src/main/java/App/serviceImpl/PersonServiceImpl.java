package App.serviceImpl;

import App.entity.Person;
import App.exception.PersonNotFoundExeption;
import App.service.PersonService;
import lombok.Data;
import org.springframework.stereotype.Service;
import App.repository.PersonRepository;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;
    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findByID(Integer id) {
        return repository.findById(id).orElseThrow(()->new PersonNotFoundExeption(id));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
