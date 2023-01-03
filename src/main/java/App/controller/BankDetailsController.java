package App.controller;

import App.entity.BankDetails;
import App.service.BankDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/rest/api/bankdetails")
@Slf4j
@Data
public class BankDetailsController {
    @Autowired
    private final BankDetailsService service;

    @Operation(summary = "save investor bank details to the database")
    @PostMapping("")
    public ResponseEntity save(BankDetails bankDetails){
        return new ResponseEntity<>(service.save(bankDetails), HttpStatus.OK);
    }
    @Operation(summary = "find all investor bank details from the database")
    @GetMapping("")
    public ResponseEntity<Iterable<BankDetails>> findAll(){
        Iterable<BankDetails> list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @Operation(summary = "find all investor bank details by id")
    @GetMapping("/{id}")
    public ResponseEntity<BankDetails>findById(@PathVariable("id") Integer id){
            return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }
}
