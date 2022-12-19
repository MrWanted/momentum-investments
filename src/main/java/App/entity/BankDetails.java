package App.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Schema(description = "The generated unique id when saved in database",
            name = "id"
    )
    private Long id;
    @Schema(description = "account number of the account holder",
            required = true)
    @Size(min = 1, max = 20)
    @Column(unique=true)
    private String accountNumber;
    @Schema(description = "the name of the bank")
    private String bankName;
    @Schema(description = "branch code of the bank")
    private String code;

    @Schema(description = "the type of the account",
            required = true,
            example = "Savings, Cheque")
    @Size(min = 13, max = 13)
    private String accountType;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
