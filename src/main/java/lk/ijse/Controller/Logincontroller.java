package lk.ijse.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.DAO.impl.UserDaoImpl;
import lk.ijse.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

public class Logincontroller {

    @FXML
    private Text SignUp1;

    @FXML
    private TextField loginUsername;

    @FXML
    private TextField loginpw;

    @FXML
    private Button login;

    private String userRole;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        User user = findUserByUsername(loginUsername.getText());

        if (user != null && new BCryptPasswordEncoder().matches(loginpw.getText(), user.getPassword())) {
            // Check role and display a message
            if (user.getRole().equals("admin") || user.getRole().equals("coordinator")) {
                new Alert(Alert.AlertType.CONFIRMATION, "Welcome back, " + user.getRole() + " " + user.getUsername() + "!").show();
                userRole = user.getRole();

                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/DashBoardForm.fxml"))));
                stage.show();
                stage.centerOnScreen();
                stage.setTitle("Dashboard");
            } else {
                new Alert(Alert.AlertType.ERROR, "You don't have the required permissions!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Oops! Invalid username or password.").show();
            System.out.println("Oops! Invalid username or password.");
        }

        Stage currentStage = (Stage) login.getScene().getWindow();
        currentStage.close();




    }

    private User findUserByUsername(String username) {
        UserDaoImpl userDao = new UserDaoImpl();
        return userDao.getUserByUsername(username);
    }
}


