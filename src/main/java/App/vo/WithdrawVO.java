package App.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WithdrawVO {
    private String investorId;
    private String productId;
    private String status;
    private Timestamp timestamp;
}
