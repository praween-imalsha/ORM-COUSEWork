package lk.ijse.BO.impl;

import lk.ijse.BO.custom.CourseBO;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.CourseDao;
import lk.ijse.DTO.CourseDto;
import lk.ijse.Entity.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {


    CourseDao courseDao = (CourseDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.COURSE);

    @Override
    public boolean saveCourse(Course entity) throws IOException {
        return courseDao.save(new Course(entity.getId(),entity.getProgramId(),entity.getProgramName(),entity.getDuration(),entity.getFee()));
    }

    @Override
    public boolean updateCourse(Course entity) throws IOException {
        return courseDao.update(new Course(entity.getId(),entity.getProgramId(),entity.getProgramName(),entity.getDuration(),entity.getFee()));
    }

    @Override
    public boolean deleteCourse(String id) throws IOException {
        return courseDao.delete(id);
    }

    @Override
    public List<Course> getAllCourse() throws IOException {

        List<Course> allCourse = courseDao.getaAll();

        return allCourse;

    }

    @Override
    public List<Course> SearchCID(String cid) throws IOException {

        return courseDao.SearchCID(cid);

    }



}
