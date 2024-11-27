package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.BO.impl.RegistrationBOImpl;
import lk.ijse.Entity.Registration;

public class PaymentForm {

    @FXML
    private AnchorPane RegistaionFome;

    @FXML
    private DatePicker datecombo;

    @FXML
    private TableView<Registration> StudentTable;

    @FXML
    private TableColumn<Registration, String> colsid;

    @FXML
    private TableColumn<Registration, String> colsname;

    @FXML
    private TableColumn<Registration, String> colcid;

    @FXML
    private TableColumn<Registration, String> colcname;

    @FXML
    private TableColumn<Registration, String> coldate;

    @FXML
    private TableColumn<Registration, String> colduration;

    @FXML
    private TableColumn<Registration, Double> colPayment;

    @FXML
    private TableColumn<Registration, Double> coldueAmonut;

    @FXML
    private TableColumn<Registration, Void> deleteBtn;

    @FXML
    private Text registaionNOtxt;

    @FXML
    private Text courseid;

    @FXML
    private TextField Paymenttxt;

    @FXML
    private Text payment;

    @FXML
    private Text payment1;

    @FXML
    private JFXComboBox<String> cmbstudentId;

    @FXML
    private Label lblstudentname;

    @FXML
    private JFXComboBox<String> cmbcourse;

    @FXML
    private Label lblregno;

    @FXML
    private Label lblprogramename;

    @FXML
    private Label lblduration;

    @FXML
    private Label lblfee;

    @FXML
    private Label lblamount;

    @FXML
    private Text RegistertaionNumber;

    private RegistrationBO registrationBO = new RegistrationBOImpl();

    // Populate the combo boxes (e.g., student IDs, courses) on initialization
  

    

   

    // Event handler for registering payment
    @FXML
    void RegisterComfirmOnAction(ActionEvent event) {
       
    }

    // Event handler for clearing the registration form
    @FXML
    void clearOnActionRegistaion(ActionEvent event) {
       
    }

  
    @FXML
    void cmbcourseOnAction(ActionEvent event) {
       
    }

    // Event handler for student combo box selection
    @FXML
    void cmbstudentOnAction(ActionEvent event) {


    }

    public void cmbcouseOnAction(ActionEvent actionEvent) {
    }
}
