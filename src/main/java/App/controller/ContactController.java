package App.controller;

import App.entity.Contact;
import App.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/rest/api/contact")
@Slf4j
public class ContactController {
    @Autowired
    private ContactService service;
    @GetMapping("/contacts")
    public ResponseEntity<Iterable<Contact>> getAllContacts(){
        Iterable<Contact> list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Optional<Contact>>findById(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact Not Found");
        }
    }
}
