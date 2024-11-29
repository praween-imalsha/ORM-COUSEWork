package lk.ijse.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationTM {

    private Long id;
    private int sid;
    private String studentName;
    private String courseid;
    private String courseName;
    private LocalDate date;
    private String duration;
    private double payment;
    private double dueAmount;
    private JFXButton delete;


}
