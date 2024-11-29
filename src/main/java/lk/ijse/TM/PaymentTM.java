package lk.ijse.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTM {

    private Long id;
    private String studentName;
    private String programName;
    private LocalDate date;
    private double payment;
    private double dueAmount;
    private JFXButton delete;


}
