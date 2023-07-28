package Controller;


import DAO.UserAccess;
import Helper.JDBC;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class LoginPageController {
    @FXML
    Button LoginButton;
    @FXML
    TextField UsernameField;
    @FXML
    PasswordField PasswordField;

    @FXML
    private void onSubmitLogin() {
        try {
            if(UserAccess.login(UsernameField.getText(), PasswordField.getText())){
            System.out.println("Pass");
            }
            else{
                System.out.println("Fail");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


