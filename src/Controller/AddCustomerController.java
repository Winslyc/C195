package Controller;

import DAO.DivisionsAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddCustomerController implements Initializable {
    @FXML TextField idTextField;
    @FXML TextField nameTextField;
    @FXML TextField addressTextField;
    @FXML TextField postalCodeTextField;
    @FXML TextField phoneTextField;
    @FXML ComboBox countryComboBox;
    @FXML ComboBox stateComboBox;



    public void setComboBoxes() throws SQLException {
      ObservableList allDivisions =  DivisionsAccess.getAllDivisions();
        ObservableList<String> countries = FXCollections.observableArrayList();
        ObservableList<String> states = FXCollections.observableArrayList();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
