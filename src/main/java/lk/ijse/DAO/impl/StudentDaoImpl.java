package lk.ijse.DAO.impl;

import lk.ijse.DAO.custom.StudentDao;
import lk.ijse.Entity.Student;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public boolean save(Student Dto) throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(Dto);


        transaction.commit();
        session.close();


        return false;


    }


    @Override
    public boolean update(Student dto) throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public boolean delete(int entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("delete from Student where id = ?1");
        nativeQuery.setParameter(1, entity);

        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public List<Student> getaAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list = session.createQuery("from Student ", Student.class).list();


        transaction.commit();
        session.close();


        return list;
    }


    @Override
    public List<Student> SearchSID(int sid) throws IOException {
        List<Student> studentModels = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentModels = session.createQuery("FROM Student WHERE id = :sid", Student.class)
                    .setParameter("sid", sid)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return studentModels;
    }

    //SELECT s.studentId FROM Student s


    

}
