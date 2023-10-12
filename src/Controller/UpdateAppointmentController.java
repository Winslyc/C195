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

import static DAO.ContactAccess.getContactName;


public class UpdateAppointmentController implements Initializable {
    @FXML private TextField appointmentID;
    @FXML private TextField appointmentTitle;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField typeTextField;
    @FXML private TextField userID;
    @FXML private TextField customerId;
    @FXML private ComboBox contactComboBox;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ComboBox startTime;
    @FXML private ComboBox endTime;
    public  static Appointment selectedAppointment;
    private int currentID;
//TODO add error handling functionality
    public void onSubmitUpdate(ActionEvent actionEvent) throws SQLException, IOException {
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
        AppointmentsAccess.updateAppointment(newAppointment);
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    private LocalDateTime convertToDateTime(LocalDate dateValue, String timeValue) {
        LocalDateTime dateTime = dateValue.atTime(TimeUtil.stringtToTime(timeValue));
        return dateTime;
    }

    public void onClickCancel(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       setAppointmentID();
        setComboBox();
        try {
            appointmentTitle.setText(selectedAppointment.getTitle());
            descriptionTextField.setText(selectedAppointment.getDescription());
            locationTextField.setText(selectedAppointment.getLocation());
            contactComboBox.setValue(getContactName(selectedAppointment.getContactID()));
            typeTextField.setText(selectedAppointment.getType());
            startDate.setValue(selectedAppointment.getStartDateandTime().toLocalDate());
            startTime.setValue(selectedAppointment.getStartDateandTime().toLocalTime().toString());
            endDate.setValue(selectedAppointment.getEndDateandTime().toLocalDate());
            endTime.setValue(selectedAppointment.getEndDateandTime().toLocalTime());
            userID.setText(String.valueOf(selectedAppointment.getUserID()));
            customerId.setText(String.valueOf(selectedAppointment.getCustomerID()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
// Auto Increments appointmentID's
    private void setAppointmentID() {
            currentID =selectedAppointment.getAppointmentId();


        appointmentID.setText(String.valueOf(currentID));
    }

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
