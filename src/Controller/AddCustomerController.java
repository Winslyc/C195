package Controller;

import DAO.DivisionsAccess;
import Model.Country;
import Model.Divisions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private ObservableList<Divisions> allDivisions;
    private ObservableList<Country> countries;

    public void setComboBoxes() throws SQLException {
        allDivisions = DivisionsAccess.getAllDivisions();
         countries = DivisionsAccess.getAllCountries();
        countries.forEach((i) -> countryComboBox.getItems().add(i.getCountryName()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setComboBoxes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML private void onSetCountry(){
        if(countryComboBox.getSelectionModel().getSelectedItem() == "U.S"){
            for(int i = 0; i<allDivisions.size(); i++){
                if(allDivisions.get(i).getCountryID() == 1){
                    System.out.println(allDivisions.get(i).getDivisionName());
                    stateComboBox.getItems().add(allDivisions.get(i).getDivisionName());
                }
            }
        }else if(countryComboBox.getSelectionModel().getSelectedItem() == "U.K"){

        }else if(countryComboBox.getSelectionModel().getSelectedItem()=="Canada"){

        }

    }
}
