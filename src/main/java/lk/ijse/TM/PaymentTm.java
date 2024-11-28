package lk.ijse.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTm {
    private String payment_id;
    private String registration_id;
    private String payment_date;
    private double upfront_payment;
    private double total_amount;
    private double due_amount;


}
