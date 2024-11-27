package lk.ijse.DAO.impl;

import lk.ijse.DAO.custom.CourseDao;
import lk.ijse.Entity.Course;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public boolean save(Course Dto) throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(Dto);


        transaction.commit();
        session.close();


        return false;


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
    public List<Course> getaAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Course> list = session.createQuery("from Course ", Course.class).list();


        transaction.commit();
        session.close();


        return list;
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
