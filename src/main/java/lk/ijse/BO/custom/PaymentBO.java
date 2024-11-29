package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Registration;


import java.io.IOException;
import java.util.List;

public interface PaymentBO extends SuperBo {
    boolean saveRegistration(Registration entity) throws IOException;

    List<Registration> getAllRegistrationDetails() throws IOException;

    boolean deleteRegistration(Long id) throws IOException;

    boolean savePayment(Payment entity) throws IOException;

    List<Payment> getAllPayment() throws IOException;

    boolean UpdatePayment(Payment entity) throws IOException;

    boolean deletePayment(Long id) throws IOException;
}
