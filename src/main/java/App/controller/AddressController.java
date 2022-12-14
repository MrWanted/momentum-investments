package App.controller;

import App.entity.Address;
import App.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/rest/api/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService service;

    @GetMapping("/addresses")
    public ResponseEntity<Iterable<Address>> getAllAddresses(){
        Iterable<Address> list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Optional<Address>>findById(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Address Not Found");
        }
    }

    @PostMapping("/address")
    public ResponseEntity save(Address address){
        return new ResponseEntity<>(service.save(address),HttpStatus.OK);
    }

}
