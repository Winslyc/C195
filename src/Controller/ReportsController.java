package Controller;

import DAO.AppointmentsAccess;
import DAO.ContactAccess;
import Helper.JDBC;
import Model.Appointment;
import Model.Report;
import com.mysql.cj.xdevapi.Table;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML private TableView report1;
    @FXML private TableView report2;
    @FXML private TableView report3;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn titleColumn;
    @FXML private TableColumn typeColumn;
    @FXML private TableColumn descriptionColumn;
    @FXML private TableColumn locationColumn;
    @FXML private TableColumn startColumn;
    @FXML private TableColumn endColumn;
    @FXML private TableColumn customerIdColumn;
    @FXML private TableColumn monthColumn;
    @FXML private TableColumn monthTypeColumn;
    @FXML private TableColumn totalAppointmentsColumn;
    @FXML private TableColumn countryColumn;
    @FXML private TableColumn totalCustomersColumn;
    @FXML private ComboBox contact;
    @FXML private Button logout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       contact.setItems(ContactAccess.getAllContacts());
    setComboBox();
        try {
            setReport2();
            setReport3();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory< Appointment,String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment,String >("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment,String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("startDateandTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment,LocalDateTime>("EndDateandTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("customerID"));

    }



    private void setReport2() throws SQLException {

        monthColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("Months"));
        monthTypeColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("Type"));
        totalAppointmentsColumn.setCellValueFactory(new PropertyValueFactory<Report,Integer>("Count"));
        report2.setItems(AppointmentsAccess.getAppointmentByTypeMonth());

    }
    private void setReport3() throws SQLException {
    countryColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("Country"));
    totalCustomersColumn.setCellValueFactory( new PropertyValueFactory<Report, Integer>("Count"));
    report3.setItems(AppointmentsAccess.getTotalCustomersByCountry());
    }

    private void setComboBox() {
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        ContactAccess.getAllContacts().forEach((i)->{
            contactNames.add( i.getContact_Name());
        });
        contact.setItems(contactNames);
    }
   public void onSelectContact(ActionEvent actionEvent) throws SQLException {
        String selectedContact = String.valueOf(contact.getSelectionModel().getSelectedItem());
        int contactID = ContactAccess.getContactID(selectedContact);
        report1.setItems(AppointmentsAccess.getAppointmentsByContact(contactID));
    }
    public void onClickExit(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/MainPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public void onClickLogout(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/LoginPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        JDBC.closeConnection();
        stage.show();

    }



    }

