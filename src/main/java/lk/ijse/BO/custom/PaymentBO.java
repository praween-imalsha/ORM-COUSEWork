package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.DTO.PaymentDTO;


import java.io.IOException;
import java.util.List;

public interface PaymentBO extends SuperBo {

    public boolean savePayment(PaymentDTO paymentDTO) throws IOException;
    public List<PaymentDTO> getAllPayments() throws IOException;
    public boolean updatePayment(PaymentDTO paymentDTO) throws IOException;
    public String getCurrentPmId() throws IOException;
    public boolean deletePayment(String paymentId) throws IOException;
}
