package service;

import App.entity.Product;
import App.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }
    @Test
    public void shouldSave() {
        final Product product = createProduct();

        repository.save(product);
        Assertions.assertThat(repository.findAll())
                .hasSize(1)
                .first();
    }
    @Test
    public void getAllProducts() throws Exception {
        Assertions.assertThat(repository.findAll())
                .hasSize(0)
                .first();
    }
    public Product createProduct(){
        // (id,name, type, balance)
        Product product = new Product();
        product.setId(1);
        product.setName("product1");
        product.setType("retirement");
        product.setBalance(BigDecimal.valueOf(35000));
        return product;
    }
}
