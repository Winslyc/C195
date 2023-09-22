package Controller;

import DAO.DivisionsAccess;
import Model.Country;
import Model.Divisions;
import javafx.collections.FXCollections;
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
    @FXML private void onSetCountry(ActionEvent event){
        ObservableList fDivisionsUS = FXCollections.observableArrayList();
        ObservableList fDivisionsUK = FXCollections.observableArrayList();
        ObservableList fDivisionsCA = FXCollections.observableArrayList();
        String selectedCountry = (String) countryComboBox.getSelectionModel().getSelectedItem();
        allDivisions.forEach(firstLevelDivision->{
            if(firstLevelDivision.getCountryID()==1){
                fDivisionsUS.add(firstLevelDivision.getDivisionName());
            } else if(firstLevelDivision.getCountryID()==2){
                fDivisionsUK.add(firstLevelDivision.getDivisionName());
            }else if(firstLevelDivision.getCountryID()==3){
                fDivisionsCA.add(firstLevelDivision.getDivisionName());
            }
        });
        if(selectedCountry == "U.S"){
            stateComboBox.setItems(fDivisionsUS);
        } else if(selectedCountry == "U.K"){
            stateComboBox.setItems(fDivisionsUK);
        } else if(selectedCountry =="Canada"){
            stateComboBox.setItems(fDivisionsCA);
        }
        }

    }

