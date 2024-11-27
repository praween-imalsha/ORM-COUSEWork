package lk.ijse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {


    private int id;

    private String firstName;
    private String lastName;

    private String address;
    private String email;


    private String phoneNumber;

    private LocalDate enrollmentDate;



}
