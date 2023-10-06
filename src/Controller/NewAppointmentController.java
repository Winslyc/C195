package Controller;

import DAO.AppointmentsAccess;
import DAO.UserAccess;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
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

    public void onSubmitAdd(){
        

    }
    public void onClickCancel(){

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

            currentID =AppointmentsAccess.getAllAppointments().size()+2;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        appointmentID.setText(String.valueOf(currentID));
    }

    private void setComboBox() {

    }
}
