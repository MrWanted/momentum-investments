package App.serviceImpl;

import App.entity.Person;
import App.service.PersonService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import App.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository repository;
    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> findByID(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
