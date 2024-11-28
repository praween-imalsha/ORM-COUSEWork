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
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String Name;
        private String address;

        @Column(unique = true)
        private String email;

        private String phoneNumber;

        private LocalDate enrollmentDate;


        public Student(int studentId) {
        }
}


