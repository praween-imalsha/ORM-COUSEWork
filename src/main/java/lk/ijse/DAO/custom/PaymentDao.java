package lk.ijse.DAO.custom;

import lk.ijse.Controller.PaymentController;
import lk.ijse.DAO.CrudDao;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Student;
import java.io.IOException;
import java.util.List;

public interface PaymentDao extends CrudDao<Payment> {
    boolean save(Payment Dto) throws IOException;



    boolean save(Student enitiy) throws IOException;

    boolean update(Payment entity) throws IOException;


    boolean delete(int id) throws IOException;

    boolean update(PaymentController entity) throws IOException;

    boolean delete(Long id) throws IOException;

    List<Payment> getaAll() throws IOException;
}
