package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate enrollmentDate;
    private Double payment;
    private Double dueAmount;
    private String studentName;
    private String programName;
    private Double duePayment;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id", nullable = false)
    private Registration registration;




}
