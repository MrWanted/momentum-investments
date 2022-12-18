package App.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawVO {
    private Integer investorId;
    private Integer productId;
    private String status;
    private BigDecimal withdrawalAmount;
    private BigDecimal currentBalance;
    private String productType;
    private int age;
}
