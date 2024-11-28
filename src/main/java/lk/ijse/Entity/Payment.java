package lk.ijse.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment {

    @Id
    private String paymentId;
    private double amount;
    private double paidAmount;
    private double fullPayment;
    private double pay;
    private double balance;


        @OneToOne(cascade = CascadeType.ALL)
      //  @Column(name = "registration")
        private Registration registration;

}
