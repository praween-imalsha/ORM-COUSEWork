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
import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Registration;
import lk.ijse.Entity.Student;
import lk.ijse.TM.RegistrationTM;
import lk.ijse.TM.StudentTM;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private TableColumn<RegistrationTM, JFXButton> deleteBtn;
    @FXML
    private Text Amountduetxt;

    @FXML
    private Text CourseDuration;

    @FXML
    private TextField Paymenttxt;

    @FXML
    private AnchorPane RegistaionFome;

    @FXML
    private Text RegistationNumbertxt;

    @FXML
    private Text RegistertaionNumber;

    @FXML
    private ComboBox<Integer> StudentIDComboBox;

    @FXML
    private ComboBox<String> StudentIDComboCourseComboBox;



    @FXML
    private TableColumn<Double, RegistrationTM> colPayment;

    @FXML
    private TableColumn<String, RegistrationTM> colcid;


    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<Double, RegistrationTM> coldueAmonut;

    @FXML
    private TableColumn<String, RegistrationTM> colduration;

    @FXML
    private TableColumn<Integer, RegistrationTM> colsid;

    @FXML
    private TableColumn<String, RegistrationTM> colsname;

    @FXML
    private Text courseName;

    @FXML
    private Text courseid;

    @FXML
    private DatePicker datecombo;

    @FXML
    private Text fee;

    @FXML
    private Text payment;

    @FXML
    private Text payment1;

    @FXML
    private Text registaionNOtxt;

    @FXML
    private Text studentMobile;

    @FXML
    private Text studentName;

    @FXML
    private Text Paidtxt;

    @FXML
    private Text studentdetails;

    @FXML
    private Text studentid;

    @FXML
    private Text topic;

    @FXML
    private TableView<RegistrationTM> RegisterTable;

    @FXML
    private TableColumn<String, RegistrationTM> colProgram;

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);

    @FXML
    void RegisterComfirmOnAction(ActionEvent event) throws SQLException, IOException {
        try {
            Long id = 0L;
            Integer studentId = StudentIDComboBox.getValue();
            String courseId = StudentIDComboCourseComboBox.getValue();
            String studentFName = studentName.getText();
            String courseFullName = courseName.getText();
            String courseDuration = CourseDuration.getText();
            double payment = Double.parseDouble(Paymenttxt.getText());
            double totalFee = Double.parseDouble(fee.getText());
            double dueAmount = totalFee - payment; //due Amount
            Amountduetxt.setText(String.valueOf(dueAmount));

            if (dueAmount == 0){
                Paidtxt.setText("Paid !");
            }
            else {
                Paidtxt.setText("");
            }

            LocalDate date = datecombo.getValue();
            Student student = registrationBO.serachbyIDS(studentId);
            Course course = registrationBO.serachbyCIDs(courseId);
            List<Payment> paymentList = new ArrayList<>();


            if (student == null) {
                new Alert(Alert.AlertType.ERROR, "Student not found!").show();
                return;
            }
            if (course == null) {
                new Alert(Alert.AlertType.ERROR, "Course not found!").show();
                return;
            }

            ObservableList<StudentTM> observableList = FXCollections.observableArrayList();



            Registration registration = new Registration(id, date, payment, dueAmount, studentFName, courseFullName, courseDuration, student, course,paymentList);

            //save
            boolean isSaved = registrationBO.saveRegistration(registration);

            if (!isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save registration.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadallvalues();
        clearTextFiled();


    }

    public void loadallvalues() throws SQLException, IOException {

        List<Registration> alldetails = registrationBO.getAllRegistrationDetails();


        for (Registration registration : alldetails) {
        }


        ObservableList<RegistrationTM> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < alldetails.size(); i++) {
            RegistrationTM registrationTM = new RegistrationTM(
                    alldetails.get(i).getId(),
                    alldetails.get(i).getStudent().getId(),
                    alldetails.get(i).getStudentName(),
                    alldetails.get(i).getCourse().getProgramId(),
                    alldetails.get(i).getCourse().getProgramName(),
                    alldetails.get(i).getEnrollmentDate(),
                    alldetails.get(i).getDuration(),

                    alldetails.get(i).getPayment(),
                    alldetails.get(i).getDueAmount(),
                    new JFXButton("delete")
            );

            observableList.add(registrationTM);
        }


        RegisterTable.setItems(observableList);

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
                confirmDialog.setHeaderText("Are you sure you want to delete this Registration?");
                confirmDialog.setContentText("Press OK to confirm or Cancel to abort.");

                confirmDialog.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        //deleteRegistration
                        boolean deleted = false;
                        try {
                            deleted = registrationBO.deleteRegistration(id);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!deleted) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Registration Deleted Successfully");
                            successAlert.showAndWait();
                            // Reload values after successful deletion
                        } else {
                            // Handle deletion failure
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Failed to delete Registration.");
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
        colsid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colcid.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        coldueAmonut.setCellValueFactory(new PropertyValueFactory<>("dueAmount"));
        deleteBtn.setCellValueFactory(new PropertyValueFactory<RegistrationTM, JFXButton>("Delete"));


    }



    @FXML
    void clearOnActionRegistaion(ActionEvent event) {

        clearTextFiled();

    }

    private void getStudentIds() {
        try {
            List<Student> allstu = registrationBO.getAllStudent();
            for (Student s : allstu) {

                boolean b = StudentIDComboBox.getItems().add(s.getId());
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getProgramID() {
        try {
            List<Course> allprogramID = registrationBO.getAllCourse();
            for (Course c : allprogramID) {

                boolean b = StudentIDComboCourseComboBox.getItems().add(c.getProgramId());
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }



    //set Customer Details
    public void comboStudetList(ActionEvent actionEvent) {

        Integer sid = StudentIDComboBox.getValue();
        try{
            Student student = registrationBO.serachbyIDS(sid);
                studentName.setText(student.getName());
            System.out.printf(student.getName());
                studentMobile.setText(student.getPhoneNumber());

        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    //set Course Details
    public void comboCourseList(ActionEvent actionEvent) {

        String cid = StudentIDComboCourseComboBox.getValue();
        try{
            Course course = registrationBO.serachbyCIDs(cid);
           courseName.setText(course.getProgramName());
           fee.setText(String.valueOf(course.getFee()));
           CourseDuration.setText(course.getDuration());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clearTextFiled(){
        studentid.setText("");
        courseid.setText("");
        courseName.setText("");
        payment.setText("");
        fee.setText("");
        studentName.setText("");
        studentMobile.setText("");
        CourseDuration.setText("");
        Paymenttxt.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getProgramID();
        getStudentIds();
        try {
            loadallvalues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setValues();
    }

}
