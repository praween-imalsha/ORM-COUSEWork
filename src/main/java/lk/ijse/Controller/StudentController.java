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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.StudentBo;
import lk.ijse.Entity.Student;
import lk.ijse.TM.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {



    @FXML
    private TableView<StudentTM> StudentTable;

    @FXML
    private Text addresstext;

    @FXML
    private Text idtext;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField addresstxt;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colfirstname;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> collastname;

    @FXML
    private TableColumn<?, ?> colnumber;

    @FXML
    private DatePicker datecombo;

    @FXML
    private Text emailtext;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField firstNametxt;

    @FXML
    private Text fntext;

    @FXML
    private TextField lastnametxt;

    @FXML
    private Text lntext;

    @FXML
    private TextField phonenumbertxt;

    @FXML
    private Text pntext;

    @FXML
    private AnchorPane studentForm;

    @FXML
    private Text topic;
    @FXML
    private TableColumn<StudentTM, JFXButton> deletebtnrow;

    @FXML
    private TableColumn<StudentTM, JFXButton> updatebtnrow;

    StudentBo studentBo = (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


    @FXML
    void clearOnActionStudent(ActionEvent event) {
        clearTextFiled();
    }




    @FXML
    void saveOnActionStudent(ActionEvent event) throws IOException, SQLException {

        int id = 0;
        String fn = firstNametxt.getText();
        String ln = lastnametxt.getText();
        String address = addresstxt.getText();
        String email = emailtxt.getText();
        String number = phonenumbertxt.getText();
        LocalDate enrollmentDate = datecombo.getValue();



        Student student = new Student(id,fn,ln,address,email,number,enrollmentDate);

        boolean s = false;

        try{

            s = studentBo.saveStudent(student);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (s){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer SAVE Success");
        }else {

            new Alert(Alert.AlertType.ERROR,"Student save UnSuccess");
        }
        loadallvalues();
        clearTextFiled();
    }


    public void loadallvalues() throws SQLException, IOException {

        List<Student> allstudent = studentBo.getAllStudent();


        for (Student student : allstudent) {
            System.out.println(student.getId() + ": " + student.getFirstName() + " - " + student.getLastName() + " - " + student.getAddress() + " - " + student.getPhoneNumber() + " - " + student.getEmail() + " - " + student.getEnrollmentDate());
        }


        ObservableList<StudentTM> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < allstudent.size(); i++) {
            StudentTM studentTM = new StudentTM(
                    allstudent.get(i).getId(),
                    allstudent.get(i).getFirstName(),
                    allstudent.get(i).getLastName(),
                    allstudent.get(i).getAddress(),
                    allstudent.get(i).getPhoneNumber(),
                    allstudent.get(i).getEmail(),
                    new JFXButton("delete"), new JFXButton("update")
            );

            observableList.add(studentTM);
        }

        StudentTable.setItems(observableList);

        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getUpdate().setStyle("-fx-background-color: #0095ff");
            observableList.get(i).getUpdate().setPrefWidth(130);
            observableList.get(i).getUpdate().setPrefHeight(30);
            observableList.get(i).getUpdate().setCursor(Cursor.HAND);
            observableList.get(i).getDelete().setStyle("-fx-background-color: #0095ff");
            observableList.get(i).getDelete().setCursor(Cursor.HAND);
            observableList.get(i).getDelete().setPrefWidth(120);
            observableList.get(i).getDelete().setPrefHeight(30);
            observableList.get(i).getUpdate().setTextFill(Color.WHITE);
            observableList.get(i).getDelete().setTextFill(Color.WHITE);
        }
        for (int i = 0; i < observableList.size(); i++) {
            int id = observableList.get(i).getId();
            observableList.get(i).getDelete().setOnAction(actionEvent -> {

                Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDialog.setTitle("Confirm Deletion");
                confirmDialog.setHeaderText("Are you sure you want to delete this customer?");
                confirmDialog.setContentText("Press OK to confirm or Cancel to abort.");

                confirmDialog.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        //deleteCustomer
                        boolean deleted = false;
                        try {
                            deleted = studentBo.deleteStudent(id);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!deleted) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Customer Deleted Successfully");
                            successAlert.showAndWait();
                            // Reload values after successful deletion
                        } else {
                            // Handle deletion failure
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Failed to delete customer.");
                            errorAlert.showAndWait();
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

            //Update Student=======

            observableList.get(i).getUpdate().setOnAction(actionEvent -> {
                int uid = Integer.parseInt(idtxt.getText());
                String fn = firstNametxt.getText();
                String ln = lastnametxt.getText();
                String address = addresstxt.getText();
                String email = emailtxt.getText();
                String number = phonenumbertxt.getText();
                LocalDate enrollmentDate = datecombo.getValue();


                Student student = new Student(uid,fn,ln,address,email,number,enrollmentDate);

                boolean s = false;

                try{

                    s = studentBo.updateStudent(student);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!s){
                    new Alert(Alert.AlertType.CONFIRMATION,"Student Update Success").show();
                }else {

                    new Alert(Alert.AlertType.ERROR,"Student Update UnSuccess").show();
                }
                try {
                    loadallvalues();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                clearTextFiled();
            });
        }
    }



    public void setValues(){
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        collastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colnumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        deletebtnrow.setCellValueFactory(new PropertyValueFactory<StudentTM, JFXButton>("Delete"));
        updatebtnrow.setCellValueFactory(new PropertyValueFactory<StudentTM, JFXButton>("Update"));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValues();
        try {
            loadallvalues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void mobilekeyRelese(KeyEvent event) {
/*
        Regex.setTextColor(org.example.lk.ijse.util.TextField.MOBILE,phonenumbertxt);
*/

    }
    @FXML
    void idkeyRelese(KeyEvent event) {
/*
        Regex.setTextColor(org.example.lk.ijse.util.TextField.ID,idtxt);
*/
    }

    @FXML
    void lastnameKeyRelese(KeyEvent event) {
/*
        Regex.setTextColor(org.example.lk.ijse.util.TextField.LNAME,lastnametxt);
*/
    }
    @FXML
    void emailKeyRelese(KeyEvent event) {
/*
        Regex.setTextColor(org.example.lk.ijse.util.TextField.EMAIL,emailtxt);
*/
    }

    @FXML
    void firstnameKeyRelese(KeyEvent event) {
/*
        Regex.setTextColor(org.example.lk.ijse.util.TextField.FNAME,firstNametxt);
*/
    }

    @FXML
    void addressKeyRelese(KeyEvent event) {

    }

    public void loadTheTextField(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            String id = String.valueOf(Integer.parseInt(idtxt.getText()));
            ArrayList<Student> students = (ArrayList<Student>) studentBo.SearchSID(Integer.parseInt(id));
            firstNametxt.setText(students.get(0).getFirstName());
            lastnametxt.setText(students.get(0).getLastName());
            addresstxt.setText(students.get(0).getAddress());
            phonenumbertxt.setText(students.get(0).getPhoneNumber());
            emailtxt.setText(students.get(0).getEmail());
        }
    }


    public void clearTextFiled(){

        idtxt.setText("");
        firstNametxt.setText("");
        lastnametxt.setText("");
        addresstxt.setText("");
        phonenumbertxt.setText("");
        emailtxt.setText("");

    }

}
