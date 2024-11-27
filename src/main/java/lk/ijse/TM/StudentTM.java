package lk.ijse.TM;

import com.jfoenix.controls.JFXButton;
import lk.ijse.Controller.StudentController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentTM extends StudentController {

    private int id;
    private  String FirstName;
    private  String LastName;
    private  String address;
    private String email;
    private  String PhoneNumber;

    private JFXButton delete;
    private JFXButton update;



}
