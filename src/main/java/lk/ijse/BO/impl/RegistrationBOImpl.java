package lk.ijse.BO.impl;

import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.CourseDao;
import lk.ijse.DAO.custom.RegistrationDAO;
import lk.ijse.DAO.custom.StudentDao;
import lk.ijse.DTO.CourseDto;
import lk.ijse.DTO.RegistrationDto;
import lk.ijse.DTO.StudentDto;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Registration;
import lk.ijse.Entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    private final RegistrationDAO registrationDAO =
            (RegistrationDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.REGISTRATION);
    private final StudentDao studentDao =
            (StudentDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.STUDENT);
    private final CourseDao courseDao =
            (CourseDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.COURSE);

    @Override
    public boolean saveRegistration(RegistrationDto dto) throws IOException {
        Registration registration = convertToEntity(dto);
        if (registration == null) {
            throw new IOException("Failed to convert RegistrationDto to Registration entity.");
        }
        return registrationDAO.save(registration);
    }

    @Override
    public boolean deleteRegistration(String registrationId) throws IOException {
        if (registrationId == null || registrationId.isEmpty()) {
            throw new IllegalArgumentException("Registration ID cannot be null or empty.");
        }
        return registrationDAO.delete(registrationId);
    }

    @Override
    public List<RegistrationDto> getAllRegistrations() throws IOException {
        List<Registration> registrations = registrationDAO.getAll();
        if (registrations == null || registrations.isEmpty()) {
            throw new IOException("No registrations found.");
        }
        List<RegistrationDto> dtos = new ArrayList<>();
        for (Registration registration : registrations) {
            dtos.add(convertToDTO(registration));
        }
        return dtos;
    }

    @Override
    public StudentDto searchStudent(String studentId) throws IOException {
        if (studentId == null || studentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        Student student = studentDao.search(studentId);
        if (student == null) {
            throw new IOException("Student not found for ID: " + studentId);
        }
        return convertToDTO(student);
    }

    @Override
    public CourseDto searchProgram(String programId) throws IOException {
        if (programId == null || programId.isEmpty()) {
            throw new IllegalArgumentException("Program ID cannot be null or empty.");
        }
        Course course = courseDao.search(programId);
        if (course == null) {
            throw new IOException("Program not found for ID: " + programId);
        }
        return convertToDTO(course);
    }

    @Override
    public String getCurrentReId() throws IOException {
        String currentId = registrationDAO.generateNewID();
        if (currentId == null || currentId.isEmpty()) {
            throw new IOException("Failed to generate a new registration ID.");
        }
        return currentId;
    }

    // Private helper methods for conversion

    private Registration convertToEntity(RegistrationDto dto) {
        if (dto == null) return null;
        return new Registration(
                dto.getRegiId(),
                dto.getEnrollmentDate(),
                dto.getPayment(),
                dto.getDueAmount(),
                new Student(dto.getStudentName()),
                new Course(dto.getProgramName(), dto.getDuration())
        );
    }

    private RegistrationDto convertToDTO(Registration registration) {
        if (registration == null) return null;
        return new RegistrationDto(
                registration.getRegiId(),
                registration.getEnrollmentDate(),
                registration.getPayment(),
                registration.getDueAmount(),
                registration.getStudent().getName(),
                registration.getCourse().getProgramName(),
                registration.getCourse().getDuration()
        );
    }

    private StudentDto convertToDTO(Student student) {
        if (student == null) return null;
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getAddress(),
                student.getPhoneNumber(),
                student.getEnrollmentDate()
        );
    }

    private CourseDto convertToDTO(Course course) {
        if (course == null) return null;
        return new CourseDto(
                course.getId(),
                course.getProgramName(),
                course.getFee(),
                course.getDuration()
        );
    }
}
