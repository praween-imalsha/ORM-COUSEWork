package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDao;
import lk.ijse.Entity.Course;

import java.io.IOException;
import java.util.List;

public interface CourseDao extends CrudDao<Course> {


    boolean delete(int id) throws IOException;

    List<Course> getaAll() throws IOException;

    List<Course> SearchCID(String cid) throws IOException;
}
