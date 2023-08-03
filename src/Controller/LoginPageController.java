package Controller;


import DAO.UserAccess;
import Helper.Alerter;
import Helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginPageController implements Initializable {
    /**
     * The Login Button
     *
     */
    @FXML
    Button LoginButton;
    /**
     * The Username Text Field
     */
    @FXML
    TextField UsernameField;
    /**
     * The Password Text Field
     */
    @FXML
    PasswordField PasswordField;
    /**
     * The Password Label
     */
    @FXML
    Label password;
    /**
     * The Username Label
     */
    @FXML
    Label Username;
    /**
     * The Location label.
      */
    @FXML
    Label Location;
    /**
     * The Date Label
     */
    @FXML
    Label Date;

    /**
     * Submits Login to SQL Server, Displays Alert if password or username is incorrect.
     * Opens The Main Page if true.
     * @param event refers to action event where the login button is clicked
     * @throws IOException
     */
    @FXML
    private void onSubmitLogin(ActionEvent event) throws IOException {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());
            if(UserAccess.login(UsernameField.getText(), PasswordField.getText())){
                Parent parent = FXMLLoader.load(getClass().getResource("/View/Main Page.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            System.out.println("Pass");
            }
            else{
                Alerter.displayErrorAlert(rb.getString("Invalid Username and Password Combination"), rb.getString("Your username or password is incorrect, please try again."));
                System.out.println("Fail");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the Login page
     * @param url refer to the location of the application
     * @param rb refers to Resource Bundle that controls language of the login page.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);

            ZoneId zoneId = ZoneId.systemDefault();
            rb =ResourceBundle.getBundle("Language/Nat", Locale.getDefault());

            Location.setText(rb.getString("Location") + " " + zoneId.getId());
            password.setText(rb.getString("Password"));
            Username.setText(rb.getString("Username"));
            UsernameField.setPromptText(rb.getString("Username"));
            PasswordField.setPromptText(rb.getString("Password"));
            LoginButton.setText(rb.getString("Login"));


    }
}


