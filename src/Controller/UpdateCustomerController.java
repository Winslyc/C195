package Controller;

import DAO.CustomerAccess;
import DAO.DivisionsAccess;
import Helper.Alerter;
import Model.Country;
import Model.Customer;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Controller.CustomerController.selectedCustomer;
import static Model.Customer.customers;


public class UpdateCustomerController implements Initializable {
    /** ID input field for the entity. */
    @FXML TextField idTextField;

    /** Name input field for the entity. */
    @FXML TextField nameTextField;

    /** Address input field for the entity. */
    @FXML TextField addressTextField;

    /** Postal code input field for the entity. */
    @FXML TextField postalCodeTextField;

    /** Phone number input field for the entity. */
    @FXML TextField phoneTextField;

    /** Country selection combo box. */
    @FXML ComboBox countryComboBox;

    /** State selection combo box. */
    @FXML ComboBox stateComboBox;

    /** Observable list for first-level divisions in the United States. */
    private ObservableList fDivisionsUS;

    /** Observable list for first-level divisions in the United Kingdom. */
    private ObservableList fDivisionsUK;

    /** Observable list for first-level divisions in Canada. */
    private ObservableList fDivisionsCA;

    /** Observable list of all first-level divisions. */
    private ObservableList<Divisions> allDivisions;

    /** Observable list of countries. */
    private ObservableList<Country> countries;

    /**
     * Returns user to the previous page
     * @param event
     * @throws IOException
     */
    public void onClickCancel(ActionEvent event) throws IOException {
        returnToCustomer(event);
    }

    /**
     * Updates users in the Database
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    public void onSubmitUpdate(ActionEvent event) throws SQLException, IOException {
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
        if(idTextField.getText().isEmpty()){
            Alerter.displayAlert("No ID","Please restart the application","");
        }else if(nameTextField.getText().isEmpty()){
            Alerter.displayAlert("Error","Name not  filled"," Please fill the name section");
        }else if(addressTextField.getText().isEmpty()){
            Alerter.displayAlert("Error","Address Empty","Please fill the Address Field");
        }else if(postalCodeTextField.getText().isEmpty()){
            Alerter.displayAlert("Error","Postal Code Empty","Please fill the Postal Code Field");
        }else if(phoneTextField.getText().isEmpty()){
            Alerter.displayAlert("Error","Phone Field Empty","Please provide your phone number in the Field marked Phone");
        } else if(divisionID == 0 ){
            Alerter.displayAlert("Error","Division Not Selected","Please Select a Country and State");
        }
        else{
            Customer updatedCustomer = new Customer(id, name, address, postal, phone,divisionID, DivisionsAccess.getDivision(divisionID));

            CustomerAccess.updateCustomer(id, updatedCustomer);
            returnToCustomer(event);
        }

    }

    /**
     * Returns Current User to the previous page
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

    /**
     * Set's the Country Combo Box
     * @throws SQLException
     */
    private void setComboBoxes() throws SQLException {
        allDivisions = DivisionsAccess.getAllDivisions();
         countries = DivisionsAccess.getAllCountries();
        countries.forEach((i) -> countryComboBox.getItems().add(i.getCountryName()));
    }

    /**
     * Initializes Text Fields
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idTextField.clear();
        nameTextField.clear();
        addressTextField.clear();
        postalCodeTextField.clear();
        stateComboBox.getSelectionModel().clearSelection();
        countryComboBox.getSelectionModel().clearSelection();
        try {
            setComboBoxes();
            idTextField.setText(String.valueOf(customers.get(customers.size()-1).getCustomerId()+1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idTextField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        nameTextField.setText(selectedCustomer.getCustomerName());
        addressTextField.setText(selectedCustomer.getAddress());
        postalCodeTextField.setText(selectedCustomer.getPostalCode());
        phoneTextField.setText(selectedCustomer.getPhoneNumber());

        int countryID = selectedCustomer.getCustomerDivision().getCountryID();
        switch(countryID){
            case 1:
                countryComboBox.setValue("U.S");
                stateComboBox.setValue(selectedCustomer.getCustomerDivision().getDivisionName());
               onSetCountry();
                break;
            case 2:
                countryComboBox.setValue("UK");
                onSetCountry();
                stateComboBox.setValue(selectedCustomer.getCustomerDivision().getDivisionName());
                break;
            case 3:
                countryComboBox.setValue("Canada");
                onSetCountry();
                stateComboBox.setValue(selectedCustomer.getCustomerDivision().getDivisionName());
                break;
        }
        }



/*    private void SetCountry(String country) {
        switch (country){
            case "U.S": stateComboBox.setItems(fDivisionsUS);
            case "U.K": stateComboBox.setItems(fDivisionsUK);
            case "Canada": stateComboBox.setItems(fDivisionsCA);
        }
    }

 */

    /**
     * Handles Event where Country Combo Box is set
     */
    @FXML private void onSetCountry(){
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

