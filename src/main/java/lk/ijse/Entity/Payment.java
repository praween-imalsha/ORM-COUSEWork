package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@Entity
public class Payment {

    @Id
    private String payment_id;
    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;
    private String payment_date;
    private double upfront_payment;
    private double total_amount;
    private double due_amount;

    public Payment(String paymentId, Registration registration, String paymentDate, double upfrontPayment, double totalAmount, double dueAmount) {
    }
}
