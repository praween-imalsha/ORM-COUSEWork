package lk.ijse.DAO;

import java.io.IOException;

public interface CrudDao<T> extends SuperDao {

    boolean save(T entity) throws IOException;

    boolean update(T entity) throws IOException;
    boolean delete(int id) throws IOException;



}
