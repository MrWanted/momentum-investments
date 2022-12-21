package App.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id", nullable = false)
    private Integer id;
    private String type;
    private String name;
    private BigDecimal balance;
    @ManyToMany(mappedBy = "products")
    private List<Person> person = new ArrayList<>(1);
}
