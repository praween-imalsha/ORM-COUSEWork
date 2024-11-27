package lk.ijse.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationTM {

    private int sid;
    private String studentName;
    private int courseid;
    private String courseName;
    private Date date;
    private String duration;
    private double payment;
    private double dueAmount;
    private JFXButton delete;

}
