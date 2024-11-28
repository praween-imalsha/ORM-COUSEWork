package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDao;
import lk.ijse.Entity.User;

import java.io.IOException;
import java.util.List;

public interface UserDao extends CrudDao<User> {

    List<User> getaAll() throws IOException;

    boolean delete(int entity) throws IOException;

    List<User> SearchUID(int uid) throws IOException;

    User getUserByUsername(String username);

}
