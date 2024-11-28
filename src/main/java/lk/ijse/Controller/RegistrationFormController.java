package lk.ijse.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.RegistrationBO;
import lk.ijse.DTO.RegistrationDto;
import lk.ijse.TM.RegistrationTM;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrationFormController {

    @FXML
    private TextField txtRegisterID;
    @FXML
    private TextField txtDate;
    @FXML
    private ComboBox<String> cmbStudentId;
    @FXML
    private ComboBox<String> cmbProgramID;
    @FXML
    private Button saveBtn;
    @FXML
    private TableView<RegistrationTM> registrationTbl;

    private final RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);

    public void initialize() throws IOException {
        loadAllRegistrations();
    }

    private void loadAllRegistrations() throws IOException {
        registrationTbl.getItems().clear();
        for (RegistrationDto dto : registrationBO.getAllRegistrations()) {
            registrationTbl.getItems().add(new RegistrationTM(
                    dto.getRegiId(),
                    dto.getStudentName(),
                    dto.getProgramName(),
                    dto.getEnrollmentDate(),
                    dto.getPayment(),
                    dto.getDueAmount()
            ));
        }
    }

    @FXML
    void saveBtnOnAction() throws IOException {
        String registerId = txtRegisterID.getText();
        LocalDate date = LocalDate.parse(txtDate.getText());
        // Collect other fields...

        RegistrationDto registrationDto = new RegistrationDto(registerId, date, ...);
        boolean isSaved = registrationBO.saveRegistration(registrationDto);
        if (isSaved) {
            loadAllRegistrations();
        } else {
            new Alert(Alert.AlertType.ERROR, "Save failed").show();
        }
    }
}
