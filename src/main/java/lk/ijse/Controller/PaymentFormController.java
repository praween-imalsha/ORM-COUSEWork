package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.DTO.PaymentDto;
import lk.ijse.TM.PayementTm;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PaymentFormController {

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TextField txtBalance;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<PayementTm> tblPayments;

    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() {
        try {
            loadAllPayments();
            generatePaymentId();
        } catch (IOException e) {
            showError(e.getMessage());
        }
    }

    private void loadAllPayments() throws IOException {
        List<PaymentDto> paymentDtos = paymentBO.getAllPayments();
        ObservableList<PayementTm> tmList = FXCollections.observableArrayList();
        for (PaymentDto dto : paymentDtos) {
            tmList.add(new PayementTm(
                    dto.getPaymentId(),
                    dto.getAmount(),
                    dto.getPaidAmount(),
                    dto.getFullPayment(),
                    dto.getPay(),
                    dto.getBalance(),
                    dto.getRegistration().getRegistrationId()
            ));
        }
        tblPayments.setItems(tmList);
    }

    private void generatePaymentId() throws IOException {
        txtPaymentId.setText(paymentBO.generatePaymentId());
    }

    @FXML
    void onSave(ActionEvent event) {
        try {
            PaymentDto paymentDto = new PaymentDto(
                    txtPaymentId.getText(),
                    Double.parseDouble(txtAmount.getText()),
                    Double.parseDouble(txtPaidAmount.getText()),
                    0, // Replace with appropriate full payment logic
                    0, // Replace with "pay" logic
                    Double.parseDouble(txtBalance.getText()),
                    null // Set the corresponding registration
            );
            if (paymentBO.save(paymentDto)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
                loadAllPayments();
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    }


