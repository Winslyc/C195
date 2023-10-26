package Controller;

import DAO.AppointmentsAccess;
import DAO.ContactAccess;
import DAO.CustomerAccess;
import Helper.JDBC;
import Model.Appointment;
import Model.Report;
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
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    /** First report table. */
    @FXML private TableView report1;

    /** Second report table. */
    @FXML private TableView report2;

    /** Third report table. */
    @FXML private TableView report3;

    /** ID column for reports. */
    @FXML private TableColumn idColumn;

    /** Title column for reports. */
    @FXML private TableColumn titleColumn;

    /** Type column for reports. */
    @FXML private TableColumn typeColumn;

    /** Description column for reports. */
    @FXML private TableColumn descriptionColumn;

    /** Location column for reports. */
    @FXML private TableColumn locationColumn;

    /** Start time column for reports. */
    @FXML private TableColumn startColumn;

    /** End time column for reports. */
    @FXML private TableColumn endColumn;

    /** Customer ID column for reports. */
    @FXML private TableColumn customerIdColumn;

    /** Month column for reports. */
    @FXML private TableColumn monthColumn;

    /** Month type column for reports. */
    @FXML private TableColumn monthTypeColumn;

    /** Total appointments column for reports. */
    @FXML private TableColumn totalAppointmentsColumn;

    /** Country column for reports. */
    @FXML private TableColumn countryColumn;

    /** Total customers column for reports. */
    @FXML private TableColumn totalCustomersColumn;

    /** Contact selection combo box. */
    @FXML private ComboBox contact;

    /**
     * Initializes Contacts combo box and Table Views
     * @param url
     * @param resourceBundle
     */
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

    /**
     * initializes report 2  Appointments By Type and Month
     * @throws SQLException
     */
    private void setReport2() throws SQLException {
        monthColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("Months"));
        monthTypeColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("Type"));
        totalAppointmentsColumn.setCellValueFactory(new PropertyValueFactory<Report,Integer>("Count"));
        report2.setItems(AppointmentsAccess.getAppointmentByTypeMonth());

    }

    /** Initializes report 3 Total Customers by Country
     * Set's Report 3
     * @throws SQLException
     */
    private void setReport3() throws SQLException {
    countryColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("Country"));
    totalCustomersColumn.setCellValueFactory( new PropertyValueFactory<Report, Integer>("Count"));
    report3.setItems(CustomerAccess.getTotalCustomersByCountry());
    }

    /**
     * Set's Combo box for Selecting Contacts
     */
    private void setComboBox() {
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        ContactAccess.getAllContacts().forEach((i)->{
            contactNames.add( i.getContact_Name());
        });
        contact.setItems(contactNames);
    }

    /**
     * Initializes Report 1 table view once a Contact is selected
     * @param actionEvent
     * @throws SQLException
     */
   public void onSelectContact(ActionEvent actionEvent) throws SQLException {
        String selectedContact = String.valueOf(contact.getSelectionModel().getSelectedItem());
        int contactID = ContactAccess.getContactID(selectedContact);
        report1.setItems(AppointmentsAccess.getAppointmentsByContact(contactID));
    }

    /**
     * Return's User to the previous page
     * @param actionEvent
     * @throws IOException
     */
    public void onClickExit(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/MainPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * Returns User to the login page and Closes DB Connection
     * @param actionEvent
     * @throws IOException
     */
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

