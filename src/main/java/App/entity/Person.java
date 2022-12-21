package App.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id", nullable = false)
    @Schema(description = "The generated ID when saved in database",
            name = "id"
    )
    private Integer id;
    @Schema(description = "first name of the investor",
            name = "first name",
            required = true,
            example = "Kamino")
    @Size(min = 3, max = 40)
    @Column(name = "name", nullable = false)
    private String firstName;

    private String surname;
    @Schema(description = "gender",
            name = "gender",
            required = true,
            example = "Male, Female, X")
    @Size(min = 1, max = 6)
    private String gender;
    @Column(name = "dateofbirth", nullable = false)
    private String dateOfBirth;
    private int age;
    @Schema(description = "identity number of the investor",
            name = "identity number",
            required = true)
    @Column(name = "identitynumber", nullable = false)
    private String identityNumber;
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
