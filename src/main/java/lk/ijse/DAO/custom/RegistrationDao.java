package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDao;
import lk.ijse.Entity.Registration;
import lk.ijse.Entity.Student;
import java.io.IOException;
import java.util.List;

public interface RegistrationDao extends CrudDao<Registration> {


    boolean save(Registration Dto) throws IOException;

    boolean save(Student enitiy) throws IOException;

    boolean update(Registration entity) throws IOException;

    boolean delete(int id) throws IOException;

    boolean delete(Long id) throws IOException;

    List<Registration> getaAll() throws IOException;

    Registration searchByRID(Long id);


}
