package Controller;

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
    @FXML protected Button addButton;
    @FXML TableView customerTable;
    @FXML TableColumn idColumn;
    @FXML TableColumn nameColumn;
    @FXML TableColumn addressColumn;
    @FXML TableColumn postalCodeColumn;
    @FXML TableColumn phoneColumn;
    @FXML TableColumn dateCreatedColumn;
    @FXML TableColumn createdByColumn;
    @FXML TableColumn lastUpdatedColumn;
    @FXML TableColumn lastUpdatedByColumn;
    @FXML TableColumn divisionIdColumn;
    public static Customer selectedCustomer;


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
    public void onClickCancel(ActionEvent actionEvent) throws IOException {
       if( Alerter.confirmAction("Cancel?","Are you sure you would like to return to the main page" )){
           Parent parent = FXMLLoader.load(getClass().getResource("/View/MainPage.fxml"));
           Scene scene = new Scene(parent);
           Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
           stage.setScene(scene);
           stage.setResizable(false);
           stage.show();

       }else{

       }

    }

    /**
     * Refreshes  List of customers in the TableView.
     * @throws SQLException
     */
    public void refreshCustomersList() throws SQLException {
        Customer.customers.setAll(CustomerAccess.selectAllCustomers());
        customerTable.setItems(Customer.customers);
    }
    public void onClickAddButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }
    public void onClickDeleteButton(ActionEvent actionEvent) throws IOException, SQLException {
    Customer toDelete = (Customer) customerTable.getSelectionModel().getSelectedItem();
        Alerter.displayAlert("Delete Succesful","Deletion Succesful ",toDelete.getCustomerName() + " has been succesfully deleted.");
    CustomerAccess.deleteCustomer(toDelete);
    refreshCustomersList();

    }

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
