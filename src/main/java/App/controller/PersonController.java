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
import java.util.Optional;

@RestController
@RequestMapping("/rest/api/investor")
@Slf4j
@Data
public class PersonController {
    private final PersonService service;

    @GetMapping("/problems/person-not-found")
    public String notfound() {
        return "you will get enough description of the problem here";
    }

    @Operation(summary = "find all investors")
    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error"),
            @ApiResponse(responseCode = "404", description = "Investors not found"),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of investors", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Person.class)),
            }
            ) })
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "find investor details by Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error"),
            @ApiResponse(responseCode = "404", description = "Investor not found"),
            @ApiResponse(responseCode = "200", description = "Successful retrieval", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Person.class)),
            }
            ) })
    public ResponseEntity<Person> findById(@PathVariable Integer id) {
        log.info("find investor details by id ...", id);
        return new ResponseEntity<>(service.findByID(id), HttpStatus.OK);
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
    @Operation(summary = "delete the investor from the database", operationId = "isAlive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error"),
            @ApiResponse(responseCode = "404", description = "Investor not found, therefore could not be deleted"),
            @ApiResponse(responseCode = "200", description = "Successful deletion", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Person.class)),
            }
            ) })
    public void deletePersonByID(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    //TODO enrol investor to products
}
