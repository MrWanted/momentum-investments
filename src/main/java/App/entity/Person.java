package App.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;
    private String gender;
    @Column(name = "dateofbirth")
    private String dateOfBirth;
    private int age;

    @Column(name = "identitynumber")
    private String idno;

    @OneToMany()
    private Set<Contact> contact = new HashSet<>(1);

    @OneToMany(cascade= CascadeType.MERGE)
    private Set<Product> products = new HashSet<>(1);

    @OneToMany()
    private Set<Address> address = new HashSet<>(1);

    @OneToOne(cascade = CascadeType.ALL)
    private BankDetails bankDetails = new BankDetails();
}
