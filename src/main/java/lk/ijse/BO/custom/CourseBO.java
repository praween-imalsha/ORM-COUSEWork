package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.DTO.CourseDto;
import lk.ijse.Entity.Course;

import java.io.IOException;
import java.util.List;

public interface CourseBO extends SuperBo {
    boolean saveCourse(Course entity) throws IOException;

    boolean updateCourse(Course entity) throws IOException;

    boolean deleteCourse(String id) throws IOException;

    List<Course> getAllCourse() throws IOException;

    List<Course> SearchCID(String cid) throws IOException;
}
