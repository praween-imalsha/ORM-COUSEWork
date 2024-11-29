
package lk.ijse.Controller;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Registration;
import lk.ijse.TM.PaymentTM;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private Text Amountduetxt;

    @FXML
    private Text DuePaymenttxt;

    @FXML
    private Text Paidtxt;

    @FXML
    private TableView<PaymentTM> PaymentTabla;

    @FXML
    private TextField Paymenttxt;

    @FXML
    private AnchorPane RegistaionFome;

    @FXML
    private ComboBox<Long> RegistationList;

    @FXML
    private Text RegistationNumbertxt;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> coldueAmonut;

    @FXML
    private TableColumn<?, ?> colsid;

    @FXML
    private TableColumn<?, ?> colsname;

    @FXML
    private Text courseName;

    @FXML
    private DatePicker datecombo;

    @FXML
    private TableColumn<PaymentTM, JFXButton> deleteBtn;

    @FXML
    private TableColumn<?, ?> deleteBtn1;

    @FXML
    private Text dueammunttxtPayment;

    @FXML
    private Text payment;

    @FXML
    private Text payment1;

    @FXML
    private Text registaionNOtxt;

    @FXML
    private Text studentName;

    @FXML
    private Text studentdetails;

    @FXML
    private Text studentid;

    @FXML
    private Text text1;

    @FXML
    private Text text11;

    @FXML
    private Text text21;

    @FXML
    private Text topic;
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.payment);

    @FXML
    void PaymentOnAction(ActionEvent event) throws SQLException, IOException {

        try {
            Long registrationId = this.RegistationList.getSelectionModel().getSelectedItem();
            String studentFName = studentName.getText();
            String courseFullName = courseName.getText();
            double dueAmount = Double.parseDouble(dueammunttxtPayment.getText());
            double payment = Double.parseDouble(Paymenttxt.getText());
            double duepayment = dueAmount - payment;
            Amountduetxt.setText(String.valueOf(duepayment));


            if (duepayment == 0) {
                Paidtxt.setText("Paid !");
            } else {
                Paidtxt.setText("");
            }

            LocalDate date = datecombo.getValue();
            if (date == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a date.").show();
                return;
            }

            Registration registrationEntity = registrationBO.serachbyRID(registrationId);
            lk.ijse.Entity.Payment payment1 = new lk.ijse.Entity.Payment(null, date, payment, dueAmount, studentFName, courseFullName, duepayment, registrationEntity);

            // Save payment
            boolean isSaved = paymentBO.savePayment(payment1);

            if (!isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save payment.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadallvalues();
        clearTextFiled();

    }

    public void clearTextFiled(){
        studentid.setText("");
        dueammunttxtPayment.setText("");
        courseName.setText("");
        payment.setText("");
        Amountduetxt.setText("");
        studentName.setText("");
        Paymenttxt.setText("");
    }
    @FXML
    void clearOnActionRegistaion(ActionEvent event) {
        clearTextFiled();

    }

    public void loadallvalues() throws SQLException, IOException {

        List<Payment> alldetails = paymentBO.getAllPayment();


        for (lk.ijse.Entity.Payment payment2 : alldetails) {
        }


        ObservableList<PaymentTM> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < alldetails.size(); i++) {
            PaymentTM paymentTM = new PaymentTM(
                    alldetails.get(i).getId(),
                    alldetails.get(i).getStudentName(),
                    alldetails.get(i).getProgramName(),
                    alldetails.get(i).getEnrollmentDate(),
                    alldetails.get(i).getPayment(),
                    alldetails.get(i).getDuePayment(),
                    new JFXButton("delete")
            );

            observableList.add(paymentTM);
        }


        PaymentTabla.setItems(observableList);

        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getDelete().setStyle("-fx-background-color: rgba(166, 7, 33)");
            observableList.get(i).getDelete().setCursor(Cursor.HAND);
            observableList.get(i).getDelete().setPrefWidth(120);
            observableList.get(i).getDelete().setPrefHeight(30);
            observableList.get(i).getDelete().setTextFill(Color.WHITE);
        }
        for (int i = 0; i < observableList.size(); i++) {
            Long id = observableList.get(i).getId();
            observableList.get(i).getDelete().setOnAction(actionEvent -> {

                Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDialog.setTitle("Confirm Deletion");
                confirmDialog.setHeaderText("Are you sure you want to delete this Payment?");
                confirmDialog.setContentText("Press OK to confirm or Cancel to abort.");

                confirmDialog.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        //deleteRegistration
                        boolean deleted = false;
                        try {
                            deleted = paymentBO.deletePayment(id);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (deleted) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Payment Deleted Successfully");
                            successAlert.showAndWait();
                            // Reload values after successful deletion
                        } else {
                            // Handle deletion failure
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Failed to delete Payment.");
                            errorAlert.showAndWait();
                        }
                        try {
                            loadallvalues();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            loadallvalues();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            });

        }
    }


    public void setValues(){
        colsid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        coldueAmonut.setCellValueFactory(new PropertyValueFactory<>("dueAmount"));
        deleteBtn.setCellValueFactory(new PropertyValueFactory<PaymentTM, JFXButton>("Delete"));


    }

    private void getRegistationIDS() {
        try {
            List<Registration> allRegistation = registrationBO.getAllRegistrationDetails();
            for (Registration r : allRegistation) {

                boolean b = RegistationList.getItems().add(r.getId());
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getRegistationIDS();
        try {
            loadallvalues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setValues();
    }

    public void RIDcomboBox(ActionEvent actionEvent) {
        Long rid =  RegistationList.getValue();
        try {
            Registration registration = registrationBO.serachbyRID(rid);
            studentName.setText(registration.getStudentName());
            courseName.setText(registration.getProgramName());
            dueammunttxtPayment.setText(String.valueOf(registration.getDueAmount()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
