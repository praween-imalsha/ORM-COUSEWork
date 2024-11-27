package lk.ijse.BO.impl;
import lk.ijse.BO.custom.StudentBo;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.StudentDao;
import lk.ijse.Entity.Student;

import java.io.IOException;
import java.util.List;

public class StudentBoImpl implements StudentBo {

    StudentDao studentDao = (StudentDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(Student entity) throws IOException {
         return studentDao.save(new Student(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getEmail(),entity.getPhoneNumber(),entity.getEnrollmentDate()));
    }


    @Override
    public boolean updateStudent(Student entity) throws IOException {
        return studentDao.update(new Student(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getEmail(),entity.getPhoneNumber(),entity.getEnrollmentDate()));
    }

    @Override
    public boolean deleteStudent(int id) throws IOException {
        return studentDao.delete(id);
    }

    @Override
    public List<Student> getAllStudent() throws IOException {

        List<Student> allStudent = studentDao.getaAll();

        return allStudent;

    }

    @Override
    public List<Student> SearchSID(int sid) throws IOException {

        return studentDao.SearchSID(sid);

    }






}
