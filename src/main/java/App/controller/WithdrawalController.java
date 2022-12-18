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

import java.math.BigDecimal;

@RestController
@RequestMapping("/rest/api/withdrawal")
@Slf4j
@Data
public class WithdrawalController {
    private final WithdrawalService service;

    @Operation(summary = "Withdrawals", description = "save and update audit trail for withdrawal")
    @PostMapping("")
    public ResponseEntity save(@RequestBody Withdrawal withdrawal) {
        log.info("Saving a withdrawal...");
        return new ResponseEntity<>(service.save(withdrawal), HttpStatus.OK);
    }
    @Operation(summary = "Withdrawals", description = "get all Investors withdrawals")
    @GetMapping("")
    public ResponseEntity<Iterable<Withdrawal>> getAllInvestorsWithdrawals(){
        Iterable<Withdrawal> list = service.findAll();
        log.info("getting all withdrawals");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @Operation(summary = "Submit Withdraw", description = "initiate withdrawal process with the given information of the investor.")
    // document validation requirements
    @PostMapping("/{investorId}/product/{productID}/amount/{amount}")
    public ResponseEntity submitWithdrawal(@PathVariable("investorId") Integer investorId, @PathVariable("productID") Integer productID,
                           @PathVariable("amount") BigDecimal withdrawalAmount){
        log.info("submitting investment withdrawal...");
        Withdrawal withdrawal = service.submitWithdrawal(investorId, productID, withdrawalAmount);
        return new ResponseEntity(withdrawal, HttpStatus.OK);
    }
    @Operation(summary = "find a  Withdrawal by id", description = "initiate withdrawal process with the given information of the investor.")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }
}
