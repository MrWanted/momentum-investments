package App.controller;

import App.entity.Person;
import App.service.PersonService;
import App.vo.WithdrawVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @Operation(summary = "Validate tax-free aggregation system health", operationId = "isAlive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "System is able to handle request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)),
                    }),
            @ApiResponse(responseCode = "403", description = "System is able to handle request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)),
                    })
    })
    @PostMapping("/investor")
    public ResponseEntity save(Person person) {
        log.info("Saving investor details...");
        return new ResponseEntity<>(service.save(person), HttpStatus.OK);
    }
}
