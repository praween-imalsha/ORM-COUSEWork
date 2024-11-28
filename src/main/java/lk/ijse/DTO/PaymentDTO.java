package lk.ijse.DTO;

import lk.ijse.Entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PaymentDTO {
    private String payment_id;
    private Registration registration;
    private String payment_date;
    private double upfront_payment;
    private double total_amount;
    private double due_amount;

}
