package App.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Schema(description = "The generated ID when saved in database",
            name = "id"
    )
    private Integer id;
    @Schema(description = "first name of the investor",
            name = "first name",
            required = true,
            example = "Kamino")
    @Size(min = 3, max = 40)
    private String firstName;

    private String surname;
    @Schema(description = "gender",
            name = "gender",
            required = true,
            example = "Male, Female, X")
    @Size(min = 1, max = 6)
    private String gender;
    private String dateOfBirth; // TODO: to be derived from identity number
    private int age;// TODO: to
    // be derived from identity number
    @Schema(description = "identity number of the investor",
            name = "first name",
            required = true,
            example = "82043000000000")
    @Size(min = 13, max = 13)
    private String identityNumber;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Contact> contact = new ArrayList<>(1);

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>(1);

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>(1);
}
