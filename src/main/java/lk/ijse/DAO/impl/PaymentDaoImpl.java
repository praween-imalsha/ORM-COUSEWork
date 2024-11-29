package lk.ijse.DAO.impl;



import lk.ijse.Controller.PaymentController;
import lk.ijse.DAO.custom.PaymentDao;
import lk.ijse.DAO.custom.RegistrationDao;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Student;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    private RegistrationDao registrationDao;
    @Override
    public boolean save(Payment Dto) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        session.save(Dto);
        transaction.commit();
        session.close();


        return false;

    }

    @Override
    public String generateNewID() throws IOException {
        return "";
    }

    @Override
    public List<Payment> getAll() throws IOException {
        return List.of();
    }

    @Override
    public String getCurrentID() throws IOException {
        return "";
    }

    @Override
    public boolean save(Student enitiy) throws IOException {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        session.update(entity);


        transaction.commit();
        session.close();


        return false;
    }

    @Override
    public boolean delete(String id) throws IOException {
        return false;
    }

    @Override
    public Payment search(String id) throws IOException {
        return null;
    }

    @Override
    public boolean delete(int id) throws IOException {
        return false;
    }

    @Override
    public boolean update(PaymentController entity) throws IOException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            Payment payment = session.get(Payment.class,id);
            session.delete(payment);
            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }



    @Override
    public List<Payment> getaAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Payment> list = session.createQuery("from Payment ", Payment.class).list();


        transaction.commit();
        session.close();


        return list;
    }
}
