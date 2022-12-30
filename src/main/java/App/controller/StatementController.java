package App.controller;

import App.entity.Statement;
import App.service.StatementService;
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
public class StatementController {
    private final StatementService service;

    @Operation(summary = "Withdrawals", description = "save and update audit trail for withdrawal")
    @PostMapping("")
    public ResponseEntity save(@RequestBody Statement withdrawal) {
        log.info("Saving a withdrawal...");
        return new ResponseEntity<>(service.save(withdrawal), HttpStatus.OK);
    }
    @Operation(summary = "Withdrawals", description = "get all Investors withdrawals")
    @GetMapping("")
    public ResponseEntity<Iterable<Statement>> getAllInvestorsWithdrawals(){
        Iterable<Statement> list = service.findAll();
        log.info("getting all withdrawals");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @Operation(summary = "Submit Withdraw", description = "initiate withdrawal process with the given information of the investor.")
    // document validation requirements
    @PostMapping("investor/{investorId}/product/{productId}/amount/{amount}")
    public ResponseEntity submitWithdrawal(@PathVariable("investorId") String investorId, @PathVariable("productId") String productId,
                           @PathVariable("amount") BigDecimal withdrawalAmount){
        log.info("submitting investment withdrawal...");
        Statement withdrawal = service.submitWithdrawal(investorId, productId, withdrawalAmount);
        return new ResponseEntity(withdrawal, HttpStatus.OK);
    }
    @Operation(summary = "find a  Withdrawal by id", description = "initiate withdrawal process with the given information of the investor.")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }
    @GetMapping("investor/{investorId}/product/{productId}")
    public ResponseEntity findByInvestorIdAndProductId(@PathVariable("investorId") String investorId, @PathVariable("productId") String productId){
        return new ResponseEntity<>(service.findByInvestorIdAndProductId(investorId, productId),HttpStatus.OK);
    }
}
