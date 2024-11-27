package lk.ijse.BO.impl;


import lk.ijse.BO.custom.UserBO;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.UserDao;
import lk.ijse.Entity.User;

import java.io.IOException;
import java.util.List;

public class UserBoImpl implements UserBO {

    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.USER);


    @Override
    public List<User> getAllUser() throws IOException {

        List<User> allUsers = userDao.getaAll();

        return allUsers;
    }
    @Override
    public boolean updateUser(User entity) throws IOException {
        return userDao.update(new User(entity.getId(),entity.getUsername(),entity.getPassword(),entity.getRole()));
    }

    @Override
    public boolean deleteUser(int id) throws IOException {
        return userDao.delete(Math.toIntExact(id));
    }

    @Override
    public List<User> SearchUID(int uid) throws IOException {

        return userDao.SearchUID(uid);

    }
}
