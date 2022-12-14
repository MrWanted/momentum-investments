package App.serviceImpl;

import App.entity.Product;
import App.repository.ProductRepository;
import App.service.ProductService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Override
    public Product save(Product address) {
        return repository.save(address);
    }
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
    @Override
    public Optional<Product> findById(Integer id) {
        return repository.findById(id);
    }
}
