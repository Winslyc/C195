package Controller;

import DAO.CustomerAccess;
import DAO.DivisionsAccess;
import DAO.UserAccess;
import Model.Country;
import Model.Customer;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static Model.Customer.*;


public class AddCustomerController implements Initializable {
    /**
     * The ID TextField
     */
    @FXML TextField idTextField;
    /**
     *  The Name Text Field
     */
    @FXML TextField nameTextField;
    /**
     * The Address Text Field
     */
    @FXML TextField addressTextField;
    /**
     * The Postal Code TextField;
     */
    @FXML TextField postalCodeTextField;
    /**
     * The Phone Text Field
     */
    @FXML TextField phoneTextField;
    /**
     * The Country Combo Box
     */
    @FXML ComboBox countryComboBox;
    /**
     * The States Combo Box
     */
    @FXML ComboBox stateComboBox;
    /**
     * List of all first level Division in the U.S
     */
    private ObservableList fDivisionsUS;
    /**
     * List of all The first Level Divisions in the UK
     */
    private ObservableList fDivisionsUK;
    /**
     * List of all the first level divisions in Canada
     */
    private ObservableList fDivisionsCA;
    /**
     * List of All Divisions
     */
    private ObservableList<Divisions> allDivisions;
    /**
     * List of All Countries
     */
    private ObservableList<Country> countries;
    /**
     * Current Division That is being added.
     */
    private Divisions currentDivision;

    /**
     * Returns you to the customer Page
     * @param event
     * @throws IOException
     */
    public void onClickCancel(ActionEvent event) throws IOException {
        returnToCustomer(event);
    }

    /**
     * Adds a customer to the SQL Database.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    public void onSubmitAdd(ActionEvent event) throws SQLException, IOException {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String postal = postalCodeTextField.getText();
        String phone = phoneTextField.getText();
        int divisionID = 0;
       for(Divisions selectedDiv: allDivisions){
           if(selectedDiv.getDivisionName() == stateComboBox.getSelectionModel().getSelectedItem()){
               divisionID = selectedDiv.getDivisionId();
           }
       }
     //   Divisions division = new Divisions(countryComboBox.getSelectionModel().getSelectedItem().toString(),)
        Customer newCustomer = new Customer(id, name, address, postal, phone,divisionID, DivisionsAccess.getDivision(divisionID));

        CustomerAccess.addcustomer(newCustomer);
        returnToCustomer(event);
    }

    /**
     * Returns to
     * @param actionEvent
     * @throws IOException
     */
    private void returnToCustomer(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private void setComboBoxes() throws SQLException {
        allDivisions = DivisionsAccess.getAllDivisions();
         countries = DivisionsAccess.getAllCountries();
        countries.forEach((i) -> countryComboBox.getItems().add(i.getCountryName()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setComboBoxes();
            idTextField.setText(String.valueOf(customers.get(customers.size()-1).getCustomerId()+2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void onSetCountry(ActionEvent event){
         fDivisionsUS = FXCollections.observableArrayList();
         fDivisionsUK = FXCollections.observableArrayList();
         fDivisionsCA = FXCollections.observableArrayList();
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
        switch (selectedCountry){
            case "U.S":
                stateComboBox.setItems(fDivisionsUS);
                break;
            case "UK":
                stateComboBox.setItems(fDivisionsUK);
                break;
            case "Canada":
                stateComboBox.setItems(fDivisionsCA);
                break;
        }
        }
    }

