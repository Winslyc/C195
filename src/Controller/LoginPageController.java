package Controller;


import Helper.JDBC;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginPageController {
    @FXML
    Button LoginButton;
    @FXML
    TextField UsernameField;
    @FXML
    PasswordField PasswordField;

    @FXML
    private void onSubmitLogin() {
        if(UsernameField.getText().equals("Admin")){
            if(PasswordField.getText().equals("Admin")){
                System.out.println("pass");
            }

        }
        else{
            System.out.println("Fail");
        }
    }
}


