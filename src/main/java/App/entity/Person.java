package App.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String surname;
    private String gender;
    private String dateOfBirth;
    private int age;

    private String idno;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Contact> contact = new ArrayList<>(1);

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "investor_product",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>(1);

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>(1);

    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
    private BankDetails bankDetails = new BankDetails();
}
