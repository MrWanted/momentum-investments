package App.controller;

import App.entity.Person;
import App.entity.Product;
import App.entity.Withdrawal;
import App.service.WithdrawalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api")
@Slf4j
@Data
public class WithdrawalController {
    //@Autowired
    private final WithdrawalService service;

    @Operation(summary = "Withdrawals", description = "save and update audit trail for withdrawal")
    @PostMapping("/withdrawal")
    public ResponseEntity save(@RequestBody Withdrawal withdrawal) {
        log.info("Saving a withdrawal...");
        return new ResponseEntity<>(service.save(withdrawal), HttpStatus.OK);
    }
    @Operation(summary = "Withdrawals", description = "get all Investors withdrawals")
    @GetMapping("/withdrawals")
    public ResponseEntity<Iterable<Withdrawal>> getAllInvestorsWithdrawals(){
        Iterable<Withdrawal> list = service.findAll();
        log.info("getting all withdrawals");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @Operation(summary = "Withdraw", description = "Create a withdrawal with the given information of the investor.")
    @PostMapping("/person/{investorId}/product/{productID}/amount/{amount}")
    public Person withdraw(@PathVariable("investorId") Integer investorId, @PathVariable("productID") String productID,
                           @PathVariable("amount") String withdrawalAmount){
        return null;
    }
}
