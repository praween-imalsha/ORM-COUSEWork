package lk.ijse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {


    private Long id;
    private Long rid;
    private LocalDate enrollmentDate;
    private Double payment;
    private Double dueAmount;
    private String studentName;
    private String programName;
    private Double duePayment;

}
