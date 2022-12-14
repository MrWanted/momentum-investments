package App.controller;

import App.entity.Person;
import App.entity.Product;
import App.exception.PersonNotFoundExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import App.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/investor")
@Slf4j
public class PersonController {
    @Autowired
    private PersonService service;
    @RequestMapping("/problems/person-not-found")
    public String notfound() {
        return "you will get enough description of the problem here";
    }

    @GetMapping("/investors")
    public List<Person> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Integer id){
        return service.findByID(id).orElseThrow(()->new PersonNotFoundExeption(id));
    }

    @PostMapping("/investor")
    public ResponseEntity save(Person person) {
        log.info("Saving investor details...");
        return new ResponseEntity<>(service.save(person), HttpStatus.OK);
    }
}
