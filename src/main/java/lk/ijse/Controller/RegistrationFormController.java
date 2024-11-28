package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CourseBO;
import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.BO.custom.StudentBo;
import lk.ijse.DTO.CourseDto;
import lk.ijse.DTO.RegistrationDTO;
import lk.ijse.DTO.StudentDto;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Student;
import lk.ijse.TM.RegistrationTm;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class RegistrationFormController {

    @FXML
    private AnchorPane registerPane;

    @FXML
    private Text courseHeading;

    @FXML
    private Pane registerDetailPane;

    @FXML
    private TextField txtRegisterID;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtStudentName;

    @FXML
    private ComboBox<String> cmbProgramID;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtProgramFee;

    @FXML
    private TextField txtUpfrontPayment;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private TableView<RegistrationTm> registrationTbl;

    @FXML
    private TableColumn<?, ?> colRegisterID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colProgramID;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableColumn<?, ?> colProgramFee;

    @FXML
    private TableColumn<?, ?> colUpfrontPayment;

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    StudentBo studentBO = (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    public void initialize() throws IOException {
        getStudentIds();
        getProgramIds();
        setDate();
        getCurrentRegisterId();
        setCellValueFactory();
        loadAllRegistrations();
        addTableSelectionListener();
    }

    private void addTableSelectionListener() {
        registrationTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getRegisterDetails(newValue);
            }
        });
    }

    private void getRegisterDetails(RegistrationTm registrationTm) {
        txtRegisterID.setText(registrationTm.getRegistrationID());
        txtDate.setText(registrationTm.getDate());
        cmbStudentId.setValue(String.valueOf(registrationTm.getId()));
        cmbProgramID.setValue(registrationTm.getProgram_id());
        txtStudentName.setText(registrationTm.getStudentName());
        txtProgramName.setText(registrationTm.getProgramName());
        txtProgramFee.setText(String.valueOf(registrationTm.getProgramFee()));
        txtUpfrontPayment.setText(String.valueOf(registrationTm.getUpfrontPayment()));
    }

    private void loadAllRegistrations() {
        ObservableList<RegistrationTm> obList = FXCollections.observableArrayList();

        try {
            List<RegistrationDTO> registerList = registrationBO.getAllRegistrations();

            for (RegistrationDTO registrationDTO : registerList) {
                RegistrationTm registrationTm = new RegistrationTm(
                        registrationDTO.getRegistrationID(),
                        registrationDTO.getDate(),
                        registrationDTO.getStudent().getId(),
                        registrationDTO.getCourse().getProgramId(),
                        registrationDTO.getStudentName(),
                        registrationDTO.getProgramName(),
                        registrationDTO.getProgramFee(),
                        registrationDTO.getUpfrontPayment()
                );
                obList.add(registrationTm);
            }
            registrationTbl.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colRegisterID.setCellValueFactory(new PropertyValueFactory<>("registrationID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
        colUpfrontPayment.setCellValueFactory(new PropertyValueFactory<>("upfrontPayment"));
    }

    private void getCurrentRegisterId() throws IOException {
        String currentId = registrationBO.getCurrentReId();
        String nextId = generateNextRegisterId(currentId);
        txtRegisterID.setText(nextId);
    }

    private String generateNextRegisterId(String currentId) {
        if (currentId != null && currentId.matches("R\\d+")) {
            int idNum = Integer.parseInt(currentId.substring(1));
            return "R" + String.format("%03d", ++idNum);
        }
        return "R001";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void getProgramIds() throws IOException {
        List<Course> courseList = courseBO.getAllCourse();

        for (Course course : courseList) {
            cmbProgramID.getItems().add(String.valueOf(course.getId()));
        }
    }

    private void getStudentIds() throws IOException {
        List<Student> studentList = studentBO.getAllStudent();

        for (Student student : studentList) {
            cmbStudentId.getItems().add(String.valueOf(student.getId()));
        }
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        try {
            if (cmbStudentId.getValue() == null || cmbStudentId.getValue().isEmpty()) {
                throw new IllegalArgumentException("Student ID cannot be empty!");
            }
            if (cmbProgramID.getValue() == null || cmbProgramID.getValue().isEmpty()) {
                throw new IllegalArgumentException("Program ID cannot be empty!");
            }

            String registerId = txtRegisterID.getText();
            String date = txtDate.getText();
            int studentId = Integer.parseInt(cmbStudentId.getValue());
            int programId = Integer.parseInt(cmbProgramID.getValue());
            String studentName = txtStudentName.getText();
            String programName = txtProgramName.getText();
            double programFee = Double.parseDouble(txtProgramFee.getText());
            double upfrontPayment = Double.parseDouble(txtUpfrontPayment.getText());

            RegistrationDTO registrationDTO = new RegistrationDTO(registerId, date,
                    new Student(studentId), new Course(programId), studentName, programName, programFee, upfrontPayment);

            boolean isSaved = registrationBO.saveRegistration(registrationDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration completed!").show();
                loadAllRegistrations();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Registration not completed!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number format!").show();
        } catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtRegisterID.clear();
        txtDate.clear();
        cmbStudentId.setValue(null);
        cmbProgramID.setValue(null);
        txtStudentName.clear();
        txtProgramName.clear();
        txtProgramFee.clear();
        txtUpfrontPayment.clear();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String registerId = txtRegisterID.getText();

        try {
            boolean isDeleted = registrationBO.deleteRegistration(registerId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration deleted!").show();
                loadAllRegistrations();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbProgramIDOnAction(ActionEvent event) throws IOException {
        String programId = cmbProgramID.getValue();

        CourseDto courseDto = registrationBO.searchProgram(programId);

        if (courseDto != null) {
            txtProgramName.setText(courseDto.getProgramName());
            txtProgramFee.setText(String.valueOf(courseDto.getFee()));
        }
        txtUpfrontPayment.requestFocus();
    }

    @FXML
    void cmbStudentIDOnAction(ActionEvent event) throws IOException {
        String studentId = cmbStudentId.getValue();

        StudentDto studentDto = registrationBO.searchStudent(studentId);

        if (studentDto != null) {
            txtStudentName.setText(studentDto.getName());
        }
        cmbProgramID.requestFocus();
    }

    @FXML
    void clearBtnOnAction(ActionEvent event) {
        clearFields();
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }
}
