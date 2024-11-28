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
import lk.ijse.BO.custom.UserBO;
import lk.ijse.Entity.User;
import lk.ijse.TM.UserTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserForm implements Initializable {

    @FXML
    private AnchorPane UserForm;

    @FXML
    private TableView<UserTM> UserTable;

    @FXML
    private TableColumn<String, UserTM> colUserName;

    @FXML
    private TableColumn<Long, UserTM> colUserPassword;

    @FXML
    private TableColumn<String, UserTM> colUserRole;

    @FXML
    private TableColumn<String, UserTM> colid;

    @FXML
    private TableColumn<UserTM, JFXButton> deletebtnrow;

    @FXML
    private Text idtext;

    @FXML
    private Text lntext;

    @FXML
    private TextField passwroddtxt;

    @FXML
    private Text role;

    @FXML
    private TextField roletxt;

    @FXML
    private Text text;

    @FXML
    private Text topic;

    @FXML
    private Text un;

    @FXML
    private TableColumn<UserTM, JFXButton> updatebtnrow;

    @FXML
    private Text userDetails;

    @FXML
    private TextField userid;

    @FXML
    private TextField usernametxt;


    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    @FXML
    public void loadTheTextField(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            String id = String.valueOf(Integer.parseInt(idtext.getText()));
            ArrayList<User> students = (ArrayList<User>) userBO.SearchUID(Integer.parseInt(id));

            usernametxt.setText(students.get(0).getUsername());
            passwroddtxt.setText(students.get(0).getPassword());
            roletxt.setText(students.get(0).getRole());

        }
    }


    @FXML
    void saveOnActionStudent(ActionEvent event) {
        String username = usernametxt.getText();
        String password = passwroddtxt.getText();
        String role = roletxt.getText();

        // Check if all fields are filled
        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
            return; // Exit the method if any field is empty
        }


        User newUser = new User(0, username, password, role);


        boolean isSaved = false;
        try {
            isSaved = userBO.saveUser(newUser);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save user").show();
            return;
        }

        // Step 4: Show the success/failure message
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "User saved successfully").show();
            try {
                loadallvalues(); // Refresh the table view to show the newly added user
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save user").show();
        }
    }

    public void loadallvalues() throws SQLException, IOException {

        List<User> allusers = userBO.getAllUser();


        for (User user : allusers) {
            System.out.println(user.getId() + ": " + user.getUsername() + " - " + user.getPassword()+ " - " + user.getRole());
        }


        ObservableList<UserTM> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < allusers.size(); i++) {
            UserTM userTM = new UserTM(
                    allusers.get(i).getId(),
                    allusers.get(i).getUsername(),
                    allusers.get(i).getPassword(),
                    allusers.get(i).getRole(),
                    new JFXButton("delete"), new JFXButton("update")
            );

            observableList.add(userTM);
        }

        UserTable.setItems(observableList);

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
            int id = observableList.get(i).getUserID();
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
                            deleted = userBO.deleteUser(id);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!deleted) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("User Deleted Successfully");
                            successAlert.showAndWait();
                            // Reload values after successful deletion
                        } else {
                            // Handle deletion failure
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Failed to delete User.");
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
                int uid = Integer.parseInt(userid.getText());
                String un = usernametxt.getText();
                String pw = passwroddtxt.getText();
                String role = roletxt.getText();



                User user = new User(uid,un,pw,role);

                boolean u = false;

                try{

                    u = userBO.updateUser(user);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!u){
                    new Alert(Alert.AlertType.CONFIRMATION,"User Update Success").show();
                }else {

                    new Alert(Alert.AlertType.ERROR,"User Update UnSuccess").show();
                }
                try {
                    loadallvalues();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    public void setValues(){
        colid.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        colUserPassword.setCellValueFactory(new PropertyValueFactory<>("UserPassword"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("UserRole"));
        deletebtnrow.setCellValueFactory(new PropertyValueFactory<UserTM, JFXButton>("Delete"));
        updatebtnrow.setCellValueFactory(new PropertyValueFactory<UserTM, JFXButton>("Update"));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
