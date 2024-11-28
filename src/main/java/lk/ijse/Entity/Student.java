package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        @ManyToOne
        private User user;
        @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Registration> registrations = new ArrayList<>();
        public Student(int studentId) {
        }

        public Student(int id, String name, String address, String email, String number, LocalDate enrollmentDate) {
        }


        public Student(String studentName) {
        }
}


