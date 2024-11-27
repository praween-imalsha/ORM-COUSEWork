package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentBo extends SuperBo {

    boolean saveStudent(Student entity) throws IOException;

    boolean updateStudent(Student entity) throws IOException;

    boolean deleteStudent(int id) throws IOException;

    List<Student> getAllStudent() throws IOException;

    List<Student> SearchSID(int sid) throws IOException;

}
