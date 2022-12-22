package App.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String type;
    private String name;
    private BigDecimal balance;
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Person> person = new ArrayList<>(1);
}
