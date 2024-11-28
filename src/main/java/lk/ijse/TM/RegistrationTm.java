package lk.ijse.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationTm{
    private String registrationID;
    private String date;
    private int id;
    private String program_id;
    private String studentName;
    private String programName;
    private double programFee;
    private double upfrontPayment;


}
