package App.controller;

import App.entity.Person;
import App.service.PersonService;
import App.vo.WithdrawVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/investor")
@Slf4j
@Data
public class PersonController {
    private final PersonService service;

    @RequestMapping("/problems/person-not-found")
    public String notfound() {
        return "you will get enough description of the problem here";
    }

    @Operation(summary = "find all investors")
    @GetMapping("")
    public List<Person> findAll() {
        return service.findAll();
    }

    @Operation(summary = "find investor details by Id")
    @GetMapping("/{id}")
    public Person findById(@PathVariable Integer id) {
        log.info("find investor details by id ...", id);
        return service.findByID(id);
    }

    @Operation(summary = "save investor details to the database", operationId = "isAlive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "System is able to handle requests",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)),
                    })
    })
    @PostMapping("")
    public ResponseEntity save(Person person) {
        log.info("Saving investor details...");
        return new ResponseEntity<>(service.save(person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePersonByID(@PathVariable("id") int id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
