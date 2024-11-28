package lk.ijse.DAO.impl;

import lk.ijse.DAO.custom.CourseDao;
import lk.ijse.Entity.Course;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public boolean save(Course course) throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(course);


        transaction.commit();
        session.close();


        return false;


    }

    @Override
    public String generateNewID() throws IOException {
        return "";
    }

    @Override
    public List<Course> getAll() throws IOException {
        return List.of();
    }

    @Override
    public String getCurrentID() throws IOException {
        return "";
    }

    @Override
    public boolean update(Course entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);


        transaction.commit();
        session.close();


        return false;
    }

    @Override
    public boolean delete(int id) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("delete from courses where programId = ?1");
        nativeQuery.setParameter(1, entity);

        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public Course search(String id) throws IOException {
        return null;
    }

    @Override
    public List<Course> getaAll() throws IOException {


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Course ");
        List<Course> programs = query.list();
        transaction.commit();
        session.close();
        return programs;
    }


    @Override
    public List<Course> SearchCID(String cid) throws IOException {
        List<Course> courseList = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            courseList = session.createQuery("FROM Course WHERE programId = :cid", Course.class)
                    .setParameter("cid", cid)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return courseList;
    }

}
