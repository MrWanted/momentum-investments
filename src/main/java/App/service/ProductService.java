package App.service;

import App.entity.Contact;
import App.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product address);
    List<Product> findAll();
    Optional<Product> findById(Integer id);
}
