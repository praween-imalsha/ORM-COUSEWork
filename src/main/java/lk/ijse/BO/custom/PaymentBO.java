package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.DTO.PaymentDto;
import lk.ijse.Entity.Registration;

import java.io.IOException;
import java.util.List;

public interface PaymentBO extends SuperBo {
    List<Registration> getRegistrationIds() throws IOException;

    double getPaidAmountByRegistrationId(Registration selectedRegistrationId) throws IOException;

    double getFullFeeByRegistrationId(Registration selectedRegistrationId) throws IOException;

    boolean save(PaymentDto paymentDto) throws IOException;

    List<PaymentDto> getAllPayments() throws IOException;

    String generatePaymentId() throws IOException;
}
