package lk.ijse.BO.impl;

import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.PaymentDAO;
import lk.ijse.DTO.PaymentDto;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO = (PaymentDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.payment);

    @Override
    public List<Registration> getRegistrationIds() throws IOException {
        return paymentDAO.getRegistrationIds();
    }

    @Override
    public double getPaidAmountByRegistrationId(Registration selectedRegistrationId) throws IOException {
        return paymentDAO.getPaidAmountByRegistrationId(selectedRegistrationId);
    }

    @Override
    public double getFullFeeByRegistrationId(Registration selectedRegistrationId) throws IOException {
        return paymentDAO.getFullFeeByRegistrationId(selectedRegistrationId);
    }

    @Override
    public boolean save(PaymentDto paymentDto) throws IOException {
        Payment payment = new Payment(
                paymentDto.getPaymentId(),
                paymentDto.getAmount(),
                paymentDto.getPaidAmount(),
                paymentDto.getFullPayment(),
                paymentDto.getPay(),
                paymentDto.getBalance(),
                paymentDto.getRegistration()
        );
        return paymentDAO.save(payment);
    }

    @Override
    public List<PaymentDto> getAllPayments() throws IOException {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(
                    payment.getPaymentId(),
                    payment.getAmount(),
                    payment.getPaidAmount(),
                    payment.getFullPayment(),
                    payment.getPay(),
                    payment.getBalance(),
                    payment.getRegistration()
            ));
        }
        return paymentDtos;
    }

    @Override
    public String generatePaymentId() throws IOException {
        return paymentDAO.generateNewID();
    }
}
