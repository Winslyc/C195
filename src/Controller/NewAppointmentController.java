package Controller;

import DAO.AppointmentsAccess;
import DAO.ContactAccess;
import DAO.UserAccess;
import Helper.TimeUtil;
import Model.User;
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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class NewAppointmentController implements Initializable {
    @FXML private TextField appointmentID;
    @FXML private TextField appointmentTitle;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField typeTextField;
    @FXML private TextField userID;
    @FXML private TextField customerId;
    @FXML private ComboBox contactComboBox;
    @FXML private DatePicker StartDate;
    @FXML private DatePicker EndDate;
    @FXML private ComboBox startTime;
    @FXML private ComboBox endTime;
    private int currentID;

    public void onSubmitAdd() throws SQLException {
        int id = Integer.parseInt(appointmentID.getText());
        String title = appointmentTitle.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        int user = Integer.parseInt(userID.getText());
        int customer = Integer.parseInt(customerId.getText());
        String selectedContact = String.valueOf(contactComboBox.getSelectionModel().getSelectedItem());
        int contactID = ContactAccess.getContactID(selectedContact);
       /* LocalDate Startdate = StartDate.getValue();
        String TimeStart = startTime.getSelectionModel().getSelectedItem().toString();
        LocalTime TimeStarted = TimeUtil.stringtToTime(TimeStart);
        LocalDateTime startDateTime = StartDate.getValue().atTime(TimeStarted);
*/

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
        userID.setText(String.valueOf(UserAccess.getCurrentUser().getUserId()));
        setComboBox();

    }
// Auto Increments appointmentID's
    private void setAppointmentID() {
        try {

            currentID =AppointmentsAccess.getAllAppointments().size()+1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
