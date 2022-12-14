package App.controller;

import App.entity.Product;
import App.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/rest/api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/product")
    public ResponseEntity save(Product product) {
        log.info("Saving a product...");
        return new ResponseEntity<>(service.save(product), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> getAllProducts(){
        Iterable<Product> list = service.findAll();
        log.info("getting all products");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>>findById(@PathVariable("id") Integer id){
        log.info("getting a product by ID: {}", id);
        try{
            return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
        }
    }
}

