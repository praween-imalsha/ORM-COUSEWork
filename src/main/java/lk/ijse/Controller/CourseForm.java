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
import lk.ijse.BO.custom.CourseBO;
import lk.ijse.Entity.Course;
import lk.ijse.TM.CourseTM;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class CourseForm implements Initializable {

    @FXML
    private AnchorPane CourseForm;

    @FXML
    private TableView<CourseTM> CourseTable;

    @FXML
    private Text Duration;

    @FXML
    private Text ProgramDetails;

    @FXML
    private TextField ProgramNametxt;

    @FXML
    private TextField Programidtxt;

    @FXML
    private TableColumn<String, CourseTM> colDuration;

    @FXML
    private TableColumn<String, CourseTM> colName;

    @FXML
    private TableColumn<Double, CourseTM> colfee;

    @FXML
    private TableColumn<?, ?> colProgramID;

    @FXML
    private TableColumn<CourseTM, JFXButton> deletebtnrow;

    @FXML
    private TextField durationtxt;

    @FXML
    private Text fee;

    @FXML
    private TextField feetxt;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text text;

    @FXML
    private Text topic;

    @FXML
    private TableColumn<CourseTM, JFXButton> updatebtnrow;

    @FXML
    void clearOnActionCourse(ActionEvent event) {

        clearTextFiled();

    }

    @FXML
    void firstnameKeyRelese(KeyEvent event) {

    }

    @FXML
    void idkeyRelese(KeyEvent event) {

    }

    @FXML
    void lastnameKeyRelese(KeyEvent event) {

    }

    @FXML
    void loadTheTextField(KeyEvent event) {

    }

    @FXML
    void mobilekeyRelese(KeyEvent event) {

    }

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @FXML
    void saveOnActionCourse(ActionEvent event) throws IOException {

        int id = 0;
        String pid = Programidtxt.getText();
        String pname = ProgramNametxt.getText();
        String duration = durationtxt.getText();
        double fee = Double.parseDouble(feetxt.getText());


        Course course = new Course(id,pid, pname, duration, fee);

        boolean c = false;

        try {

            c = courseBO.saveCourse(course);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer SAVE Success");
        } else {

            new Alert(Alert.AlertType.ERROR, "Student save UnSuccess");
        }
        loadallvalues();
        clearTextFiled();

    }

    private void loadallvalues() throws IOException {

        List<Course> allcourse = courseBO.getAllCourse();


        for (Course course : allcourse) {
            System.out.println(course.getId() + course.getProgramId() + ": " + course.getProgramName() + " - " + course.getDuration() + " - " + course.getFee());
        }


        ObservableList<CourseTM> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < allcourse.size(); i++) {
            CourseTM courseTM = new CourseTM(
                    allcourse.get(i).getProgramId(),
                    allcourse.get(i).getProgramName(),
                    allcourse.get(i).getFee(),
                    allcourse.get(i).getDuration(),
                    new JFXButton("delete"), new JFXButton("update")
            );

            observableList.add(courseTM);
        }

        CourseTable.setItems(observableList);

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
            String id = observableList.get(i).getProgramID();
            observableList.get(i).getDelete().setOnAction(actionEvent -> {

                Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDialog.setTitle("Confirm Deletion");
                confirmDialog.setHeaderText("Are you sure you want to delete this Course?");
                confirmDialog.setContentText("Press OK to confirm or Cancel to abort.");

                confirmDialog.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        //deleteCourse
                        boolean deleted = false;
                        try {
                            deleted = courseBO.deleteCourse(id);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!deleted) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Course Deleted Successfully");
                            successAlert.showAndWait();
                            // Reload values after successful deletion
                        } else {
                            // Handle deletion failure
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Failed to delete course.");
                            errorAlert.showAndWait();
                        }
                        try {
                            loadallvalues();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            });

            //Update Course=======

            observableList.get(i).getUpdate().setOnAction(actionEvent -> {
                int ccid = 0;
                String cid = Programidtxt.getText();
                String name = ProgramNametxt.getText();
                String duration = durationtxt.getText();
                double fee = Double.parseDouble(feetxt.getText());


                Course course = new Course(ccid,cid, name, duration, fee);

                boolean c = false;

                try {

                    c = courseBO.updateCourse(course);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!c) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course Update Success").show();
                } else {

                    new Alert(Alert.AlertType.ERROR, "Course Update UnSuccess").show();
                }
                try {
                    loadallvalues();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                clearTextFiled();
            });
        }
    }


    public void setValues() {
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("ProgramID"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colfee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        deletebtnrow.setCellValueFactory(new PropertyValueFactory<CourseTM, JFXButton>("Delete"));
        updatebtnrow.setCellValueFactory(new PropertyValueFactory<CourseTM, JFXButton>("Update"));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadallvalues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setValues();
    }


    public void loadTheTextFiled(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String id = Programidtxt.getText();
            ArrayList<Course> courses = (ArrayList<Course>) courseBO.SearchCID(id);

            Programidtxt.setText(courses.get(0).getProgramId());
            ProgramNametxt.setText(courses.get(0).getProgramName());
            durationtxt.setText(courses.get(0).getDuration());
            feetxt.setText(String.valueOf(courses.get(0).getFee()));


        }
    }

    public void clearTextFiled(){
        Programidtxt.setText("");
        ProgramNametxt.setText("");
        durationtxt.setText("");
        feetxt.setText("");
    }
}



