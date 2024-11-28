package lk.ijse.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Registration {

    @Id
    private String registrationID;
    private String date;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Course course;
    private String studentName;
    private String programName;
    private double programFee;
    private double upfrontPayment;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    List<Payment> payments;


    public Registration(String registrationID, String date, Student student, Course course, String studentName, String programName, double programFee, double upfrontPayment) {
    }
}
