package lk.ijse.DAO.impl;

import lk.ijse.DAO.custom.PaymentDAO;
import lk.ijse.Entity.Payment;
import lk.ijse.config.FactoryConfiguration;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment payment) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object isSaved = session.save(payment);

        if(isSaved != null){
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public List<Payment> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Payment");
        List<Payment> payments = query.list();
        transaction.commit();
        session.close();
        return payments;
    }

    @Override
    public String getCurrentID() throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select payment_id from Payment ORDER BY payment_id DESC LIMIT 1");
        String currentId = (String) query.uniqueResult();

        transaction.commit();
        session.close();
        return currentId;
    }

    @Override
    public boolean update(Payment payment) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(payment);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from Payment where payment_id = ?1");
        query.setParameter(1, id);
        boolean isDeleted = query.executeUpdate() > 0;

        if (isDeleted) {
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public Payment search(String id) {
        return null;
    }
}
