package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.DTO.RegistrationDto;
import lk.ijse.DTO.StudentDto;
import lk.ijse.DTO.CourseDto;

import java.io.IOException;
import java.util.List;

public interface RegistrationBO extends SuperBo {
    boolean saveRegistration(RegistrationDto registration) throws IOException;

    boolean deleteRegistration(String registrationId) throws IOException;

    List<RegistrationDto> getAllRegistrations() throws IOException;

    StudentDto searchStudent(String studentId) throws IOException;

    CourseDto searchProgram(String programId) throws IOException;

    String getCurrentReId() throws IOException;
}
