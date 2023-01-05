package App.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@SequenceGenerator(name = "sequence", sequenceName = "statement-sequence")
public class Statement extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Integer id;
    @Column(name = "investor_id")
    private String investorId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "available_balance")
    private BigDecimal balance;
    @Column(name = "withdrawal_amount")
    private BigDecimal withdrawalAmount;
    private String status;
}
