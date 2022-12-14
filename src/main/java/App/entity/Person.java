package App.entity;

import jakarta.persistence.*;
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
    private Long id;
    private String firstName;
    private String surname;
    private String gender;//Male, Female, X
    private String dateOfBirth;
    private String identityNumber;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Contact> contact = new ArrayList<>(1);

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>(1);

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>(1);
}
