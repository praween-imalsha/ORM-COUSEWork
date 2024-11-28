package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDao;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Registration;

import java.io.IOException;
import java.util.List;

public interface PaymentDAO extends CrudDao<Payment> {
    List<Registration> getRegistrationIds() throws IOException;

    double getPaidAmountByRegistrationId(Registration registration) throws IOException;

    double getFullFeeByRegistrationId(Registration registration) throws IOException;
}
