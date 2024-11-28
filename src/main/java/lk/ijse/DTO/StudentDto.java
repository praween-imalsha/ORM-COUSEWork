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

    private String Name;


    private String address;
    private String email;


    private String phoneNumber;

    private LocalDate enrollmentDate;


    public StudentDto(int id, String Name,  String address, String email, LocalDate enrollmentDate) {
    }
}
