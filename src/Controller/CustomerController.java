package Controller;

import DAO.AppointmentsAccess;
import DAO.CustomerAccess;
import Helper.Alerter;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    /** Add Button for new customers. */
    @FXML protected Button addButton;

    /** Table displaying customers. */
    @FXML TableView customerTable;

    /** Customer ID column. */
    @FXML TableColumn idColumn;

    /** Customer name column. */
    @FXML TableColumn nameColumn;

    /** Address column. */
    @FXML TableColumn addressColumn;

    /** Postal code column. */
    @FXML TableColumn postalCodeColumn;

    /** Phone number column. */
    @FXML TableColumn phoneColumn;

    /** Date created column. */
    @FXML TableColumn dateCreatedColumn;

    /** Created by column. */
    @FXML TableColumn createdByColumn;

    /** Last updated date column. */
    @FXML TableColumn lastUpdatedColumn;

    /** Last updated by column. */
    @FXML TableColumn lastUpdatedByColumn;

    /** Customer division ID column. */
    @FXML TableColumn divisionIdColumn;

    /** Currently selected customer. */
    public static Customer selectedCustomer;

    /**
     * Opens the Update Customer Page
     * @param actionEvent
     * @throws IOException
     */
    public void updateCustomer(ActionEvent actionEvent) throws IOException {
        selectedCustomer= (Customer) customerTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null){
            Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateCustomer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            Alerter.displayErrorAlert("No Customer Selected", "Please select a Customer to edit.");
        }
    }

    /**
     * Returns the user to the main Page
     * @param actionEvent
     * @throws IOException
     */
    public void onClickCancel(ActionEvent actionEvent) throws IOException {

           Parent parent = FXMLLoader.load(getClass().getResource("/View/MainPage.fxml"));
           Scene scene = new Scene(parent);
           Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
           stage.setScene(scene);
           stage.setResizable(false);
           stage.show();

    }

    /**
     * Refreshes  List of customers in the TableView.
     * @throws SQLException
     */
    public void refreshCustomersList() throws SQLException {
        Customer.customers.setAll(CustomerAccess.selectAllCustomers());
        customerTable.setItems(Customer.customers);
    }

    /**
     * Opens the Add Customer Page
     * @param actionEvent
     * @throws IOException
     */
    public void onClickAddButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    /**
     * Deletes Selected Customer from the database.
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void onClickDeleteButton(ActionEvent actionEvent) throws IOException, SQLException {
    Customer toDelete = (Customer) customerTable.getSelectionModel().getSelectedItem();
    if(AppointmentsAccess.CheckforCustomerAppointments(toDelete)){
        Alerter.displayAlert("Delete Succesful","Deletion Succesful ",toDelete.getCustomerName() + " has been succesfully deleted.");
        CustomerAccess.deleteCustomer(toDelete);
    } else {
        Alerter.displayErrorAlert("Failure", "This Customer still has Appointments. Please Delete all appointments before trying again.");
    }
        refreshCustomersList();

    }

    /**
     * Initializes the Customer Page including Table Views.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
           refreshCustomersList();
        } catch (SQLException e) {
System.out.println(e);
        }
        customerTable.setItems(Customer.customers);
        idColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<Customer, Date>("createDate"));
        createdByColumn.setCellValueFactory( new PropertyValueFactory<Customer, String>("createdBy"));
        lastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<Customer, Date>("lastUpdate"));
        lastUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastUpdateBy"));
        divisionIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("division"));
    }
}
