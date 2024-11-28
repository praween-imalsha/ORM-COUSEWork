package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.DTO.CourseDto;
import lk.ijse.DTO.RegistrationDTO;
import lk.ijse.DTO.StudentDto;

import java.io.IOException;
import java.util.List;

public interface RegistrationBO extends SuperBo {

    public StudentDto searchStudent(String studentId) throws IOException;
    public CourseDto searchProgram(String programId) throws IOException;
    public boolean saveRegistration(RegistrationDTO registrationDTO) throws IOException;
    public String getCurrentReId() throws IOException;
    public List<RegistrationDTO> getAllRegistrations() throws IOException;
    public boolean updateRegistration(RegistrationDTO registrationDTO) throws IOException;
    public boolean deleteRegistration(String registerId) throws IOException;
    public RegistrationDTO searchRegistration(String registerId) throws IOException;
}
