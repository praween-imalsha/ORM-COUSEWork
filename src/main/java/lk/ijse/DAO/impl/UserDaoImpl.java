package lk.ijse.DAO.impl;

import lk.ijse.DAO.custom.UserDao;
import lk.ijse.Entity.User;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back if there was an error
            }
            e.printStackTrace(); // Consider logging this instead of printing
        }
    }


    public User getUserByUsername(String username) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<User> getaAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> list = session.createQuery("from User ", User.class).list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public boolean save(User user) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(user);  // Save the user object
            transaction.commit();
            return true;  // Return true if user is saved successfully
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback on error
            }
            e.printStackTrace();
            return false;  // Return false on failure
        } finally {
            session.close();  // Always close the session
        }
    }

    @Override
    public String generateNewID() throws IOException {
        return "";
    }

    @Override
    public List<User> getAll() throws IOException {
        return List.of();
    }

    @Override
    public String getCurrentID() throws IOException {
        return "";
    }


    @Override
    public boolean update(User dto) throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public boolean delete(String id) throws IOException {
        return false;
    }

    @Override
    public User search(String id) throws IOException {
        return null;
    }


    @Override
    public boolean delete(int entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("delete from users where id = ?1");
        nativeQuery.setParameter(1, entity);

        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public List<User> SearchUID(int uid) throws IOException {
        List<User> users = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            users = session.createQuery("FROM User WHERE id = :uid", User.class)
                    .setParameter("id", uid)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return users;
    }
}