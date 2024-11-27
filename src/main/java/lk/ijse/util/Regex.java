package lk.ijse.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text) {
        String filed = "";
        switch (textField) {
            case ID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case MOBILE:
                filed = "^0\\d{9}$";
                break;
            case FNAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case LNAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";


        }
        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-border-color: green");
            textField.setStyle("-fx-border-color: green");
            return true;
        }else {
            textField.setStyle("-fx-border-color: Red");
            textField.setStyle("-fx-border-color: Red");
            return false;
        }
    }
}




