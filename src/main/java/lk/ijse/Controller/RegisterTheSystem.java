package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.Entity.User;
import lk.ijse.UserService;

import java.io.IOException;

public class RegisterTheSystem extends UserService {

    @FXML
    private Text SignUp;

    @FXML
    private Button login;

    @FXML
    private TextField loginUsername;

    @FXML
    private TextField loginpw;

    @FXML
    private TextField passwordtxt1;

    @FXML
    private TextField role1;

    @FXML
    private Button signUpbtn;

    @FXML
    private TextField usernametxt1;

    @FXML
    void SignUpOnAction(ActionEvent event) {
        String username = usernametxt1.getText();
        String password = passwordtxt1.getText();
        String role = role1.getText();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        UserService userService = new UserService();
        userService.registerUser(username, password, role);
    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {


    }


    public void logOnaction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/Login.fxml"))));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
    }
}
