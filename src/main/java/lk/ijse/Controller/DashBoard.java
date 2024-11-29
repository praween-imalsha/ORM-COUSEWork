package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.DAO.DaoFactory;
import lk.ijse.DAO.custom.UserDao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoard implements Initializable {

    @FXML
    private AnchorPane ButtonPane;

    @FXML
    private Button CoourseManagmentbtn;

    @FXML
    private AnchorPane Emptypane;

    @FXML
    private Button StudentManagmentBtn;

    @FXML
    private Button logOutbtn;

    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.USER);
    @FXML
    private Button DashBoardbtn;

    @FXML
    void CourseManagmentOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CourseForm.fxml"));
        Emptypane.getChildren().setAll(parent);
    }


    @FXML
    void StudentManageOnAction(ActionEvent event) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/View/StudentForm.fxml"));
        Emptypane.getChildren().setAll(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/AdminDashBoard.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Emptypane.getChildren().clear();
        Emptypane.getChildren().add(root);
    }


    @FXML
    void DashBoradOnAction(ActionEvent event) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/View/AdminDashBoard.fxml"));
        Emptypane.getChildren().setAll(parent);
    }

    public void PaymentManagmentOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/View/paymentFrom.fxml"));
        Emptypane.getChildren().setAll(parent);
    }


    @FXML
    void logOutTheSystem(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/RegisterTheSystem.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Login");

        Stage currentStage = (Stage) ButtonPane.getScene().getWindow();
        currentStage.close();
    }

    public void UserManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/UserForm.fxml"));
        Emptypane.getChildren().setAll(parent);
    }

    public  void RegestationOnAction( ) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Register.fxml"));
        Emptypane.getChildren().setAll(parent);
    }
}

