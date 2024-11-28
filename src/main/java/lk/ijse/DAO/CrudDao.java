package lk.ijse.DAO;

import java.io.IOException;
import java.util.List;

public interface CrudDao<T> extends SuperDao {

    public boolean save(T entity) throws IOException;

    public List<T> getAll() throws IOException;
    public String getCurrentID() throws IOException;
    public boolean update(T entity) throws IOException;
    public boolean delete(String id) throws IOException;
    public  T search(String id) throws IOException;



}
