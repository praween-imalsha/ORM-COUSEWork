package lk.ijse.DAO.impl;

import lk.ijse.DAO.custom.PaymentDAO;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Registration;
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
        try {
            session.save(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new IOException("Failed to save payment.", e);
        } finally {
            session.close();
        }
    }

    @Override
    public String generateNewID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<String> query = session.createQuery(
                    "SELECT paymentId FROM Payment ORDER BY paymentId DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();
            transaction.commit();

            if (lastId != null && lastId.startsWith("P")) {
                int newId = Integer.parseInt(lastId.substring(1)) + 1;
                return "P" + String.format("%03d", newId);
            }
            return "P001";
        } catch (Exception e) {
            transaction.rollback();
            throw new IOException("Failed to generate new ID.", e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Payment> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Payment> payments = session.createQuery("FROM Payment", Payment.class).list();
            transaction.commit();
            return payments;
        } catch (Exception e) {
            transaction.rollback();
            throw new IOException("Failed to fetch payments.", e);
        } finally {
            session.close();
        }
    }

    @Override
    public String getCurrentID() throws IOException {
        return "";
    }

    @Override
    public boolean update(Payment entity) throws IOException {
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
    public List<Registration> getRegistrationIds() throws IOException {
        return List.of();
    }

    @Override
    public double getPaidAmountByRegistrationId(Registration registration) throws IOException {
        return 0;
    }

    @Override
    public double getFullFeeByRegistrationId(Registration registration) throws IOException {
        return 0;
    }

    // Implement other methods as needed...
}
