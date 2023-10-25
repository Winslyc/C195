package Controller;

import DAO.AppointmentsAccess;
import DAO.ContactAccess;
import Helper.TimeUtil;
import Model.Appointment;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class NewAppointmentController implements Initializable {
 /** Appointment ID input field. */
    @FXML private TextField appointmentID;
    /** Appointment title input field. */
    @FXML private TextField appointmentTitle;
    /** Description input field. */
    @FXML private TextField descriptionTextField;
    /** Location input field. */
    @FXML private TextField locationTextField;
    /** Type input field. */
    @FXML private TextField typeTextField;
    /** User ID input field. */
    @FXML private TextField userID;
    /** Customer ID input field. */
    @FXML private TextField customerId;
    /** Contact selection combo box. */
    @FXML private ComboBox contactComboBox;
    /** Start date picker. */
    @FXML private DatePicker startDate;
    /** End date picker. */
    @FXML private DatePicker endDate;
    /** Start time selection combo box. */
    @FXML private ComboBox startTime;
    /** End time selection combo box. */
    @FXML private ComboBox endTime;
    /**
     * Grabs the currently Selected Appointment ID to edit.
     */
    private int currentID;
//TODO add error handling functionality

    /**
     * Adds Customer to the Database
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onSubmitAdd(ActionEvent actionEvent) throws SQLException, IOException {
        int id = Integer.parseInt(appointmentID.getText());
        String title = appointmentTitle.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        int user = Integer.parseInt(userID.getText());
        int customer = Integer.parseInt(customerId.getText());
        String selectedContact = String.valueOf(contactComboBox.getSelectionModel().getSelectedItem());
        int contactID = ContactAccess.getContactID(selectedContact);
        LocalDateTime startDateTime = convertToDateTime(startDate.getValue(), startTime.getSelectionModel().getSelectedItem().toString());
        LocalDateTime endDateTime= convertToDateTime(endDate.getValue(), endTime.getSelectionModel().getSelectedItem().toString());
        Appointment newAppointment = new Appointment(id, title, description, location, type, startDateTime,endDateTime,customer,user,contactID);
        AppointmentsAccess.addNewAppointment(newAppointment);
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Converts  Start date and time to a LocalDateTime value.
     * @param dateValue
     * @param timeValue
     * @return
     */
    private LocalDateTime convertToDateTime(LocalDate dateValue, String timeValue) {
        LocalDateTime dateTime = dateValue.atTime(TimeUtil.stringtToTime(timeValue));
        return dateTime;
    }

    /**
     * Returns  the User to the Appointment Page
     * @param actionEvent
     * @throws IOException
     */
    public void onClickCancel(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
/**
 * Initializes Combo Boxes
 */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       setAppointmentID();
        setComboBox();

    }

    /**
     * Set's Appointment ID for new appointments
     */
// Auto Increments appointmentID's
    private void setAppointmentID() {
        try {

            currentID =AppointmentsAccess.getAllAppointments().size()+1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        appointmentID.setText(String.valueOf(currentID));
    }
    /**
     * Set's Combo Boxes
     */
    private void setComboBox() {
        //SETS CONTACT NAMES List
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        ContactAccess.getAllContacts().forEach((i)->{
           contactNames.add( i.getContact_Name());
        });
        contactComboBox.setItems(contactNames);
        // SETS TIME COMBO BOXES /// Business hours == 8AM to 10PM EST
        ObservableList<String> appointmentTimes = FXCollections.observableArrayList();


        LocalTime firstAppointment = LocalTime.MIN.plusHours(8);
        LocalTime lastAppointment = LocalTime.MAX.minusHours(1).minusMinutes(45);

        if(!firstAppointment.equals(0) || !lastAppointment.equals(0)){
            while(firstAppointment.isBefore(lastAppointment)){
                appointmentTimes.add(String.valueOf(firstAppointment));

                firstAppointment =firstAppointment.plusMinutes(15);

            }
        }
        startTime.setItems(appointmentTimes);
        endTime.setItems(appointmentTimes);


    }
}
