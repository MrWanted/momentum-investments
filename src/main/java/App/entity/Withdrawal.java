package App.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Withdrawal extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String investorId;
    private String productId;
    @Column(name = "withdrawal-amount")
    private BigDecimal withdrawalAmount;
    private String status;
}
