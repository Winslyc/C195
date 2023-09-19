package Controller;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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


    public void onClickAddButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTable.setItems(Customer.Customers);
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
