package lk.ijse.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PayementTm {

    private String paymentId;
   // private String regiId;
    private double amount;
    private double paidAmount;
    private double fullPayment;
    private double pay;
    private double balance;
    private String registration_regiId;
}
