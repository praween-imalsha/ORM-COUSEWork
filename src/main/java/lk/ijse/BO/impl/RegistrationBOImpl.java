package lk.ijse.BO.impl;

import lk.ijse.BO.SuperBo;
import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.CourseDao;
import lk.ijse.DAO.custom.RegistrationDAO;
import lk.ijse.DAO.custom.StudentDao;
import lk.ijse.DTO.CourseDto;
import lk.ijse.DTO.RegistrationDTO;
import lk.ijse.DTO.StudentDto;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Registration;
import lk.ijse.Entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RegistrationBOImpl implements RegistrationBO {


    StudentDao studentDAO = (StudentDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.STUDENT);
    CourseDao courseDao = (CourseDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.COURSE);
    RegistrationDAO registrationDAO = (RegistrationDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.REGISTRATION);

    @Override
    public StudentDto searchStudent(String studentId) throws IOException {
        Student student = studentDAO.search(studentId);
        if (student == null) {
            // Handle the case where the student is not found
            // You can either return null, throw an exception, or handle it appropriately
            throw new IOException("Student with ID " + studentId + " not found.");
        }
        return new StudentDto(student.getId(), student.getName(), student.getAddress(), student.getEmail(), student.getEnrollmentDate());
    }

    @Override
    public CourseDto searchProgram(String programId) throws IOException {
        Course course = courseDao.search(programId);
        return new CourseDto(course.getProgramId(), course.getProgramName(), course.getDuration(), course.getFee());
    }

    @Override
    public boolean saveRegistration(RegistrationDTO registrationDTO) throws IOException {
        return registrationDAO.save(new Registration(registrationDTO.getRegistrationID(), registrationDTO.getDate(), registrationDTO.getStudent(), registrationDTO.getCourse(), registrationDTO.getStudentName(), registrationDTO.getProgramName(), registrationDTO.getProgramFee(), registrationDTO.getUpfrontPayment()));
    }

    @Override
    public String getCurrentReId() throws IOException {
        return registrationDAO.getCurrentID();
    }

    @Override
    public List<RegistrationDTO> getAllRegistrations() throws IOException {
        List<Registration> registrations = registrationDAO.getAll();
        List<RegistrationDTO> regList = new ArrayList<>();

        for (Registration registration : registrations) {
            RegistrationDTO registrationDTO = new RegistrationDTO(registration.getRegistrationID(), registration.getDate(), registration.getStudent(), registration.getCourse(), registration.getStudentName(), registration.getProgramName(), registration.getProgramFee(), registration.getUpfrontPayment());
            regList.add(registrationDTO);
        }
        return regList;
    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) throws IOException {
        return registrationDAO.update(new Registration(registrationDTO.getRegistrationID(), registrationDTO.getDate(), registrationDTO.getStudent(), registrationDTO.getCourse(), registrationDTO.getStudentName(), registrationDTO.getProgramName(), registrationDTO.getProgramFee(), registrationDTO.getUpfrontPayment()));
    }

    @Override
    public boolean deleteRegistration(String registerId) throws IOException {
        return registrationDAO.delete(registerId);
    }

    @Override
    public RegistrationDTO searchRegistration(String registerId) throws IOException {
        Registration registration = registrationDAO.search(registerId);
        return new RegistrationDTO(registration.getRegistrationID(), registration.getDate(), registration.getStudent(), registration.getCourse(), registration.getStudentName(), registration.getProgramName(), registration.getProgramFee(), registration.getUpfrontPayment());
    }
}