package lk.ijse.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    @Id
    private String id;

    private String programName;

    private String duration;

    private double fee;

    public CourseDto(int id, String programName, double fee, String duration) {
    }
}
