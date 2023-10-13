package Controller;

import DAO.AppointmentsAccess;
import Helper.Alerter;
import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {
  @FXML TableView appointmentsTable;
  @FXML TableColumn appointmentID;
  @FXML TableColumn title;
  @FXML TableColumn description;
  @FXML TableColumn location;
  @FXML TableColumn contact;
  @FXML TableColumn type;
  @FXML TableColumn startDateandTime;
  @FXML TableColumn endDateandTime;
  @FXML TableColumn customerID;
  @FXML TableColumn userID;
  @FXML RadioButton monthRadioButton;
  @FXML RadioButton weekRadioButton;


  public void onSubmitNew(ActionEvent actionEvent) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
    Scene scene = new Scene(parent);
    Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }
  public void onSubmitDelete(ActionEvent actionEvent) throws SQLException {
    Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
    if(AppointmentsAccess.deleteAppointment(selectedAppointment)){
      Alerter.displayAlert("Deletion Succesful", "You have deleted Appointment " + String.valueOf(selectedAppointment.getAppointmentId()) + " This was an " +
              selectedAppointment.getType() + " type of appointment. " , "Thank You.");
      appointmentsTable.setItems(AppointmentsAccess.getAllAppointments());
    }
    else{
      Alerter.displayErrorAlert("Delete Unsuccessful", "Please try again your deletion of Appointment " + selectedAppointment.getAppointmentId() + " was unsuccesful.");
    }


  }
  public void resetFilters(ActionEvent actionEvent){

  }
  public void onSelectRadioButton(ActionEvent actionEvent){

  }
  public void onSubmitEdit(ActionEvent actionEvent) throws  IOException{
    UpdateAppointmentController.selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
    if(UpdateAppointmentController.selectedAppointment != null) {
      Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateAppointment.fxml"));
      Scene scene = new Scene(parent);
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
    else {
      Alerter.displayErrorAlert("Appointment not selected", "There is no selectedAppointment selected.");
    }
  }
 public void onSubmitBack(ActionEvent actionEvent) throws IOException {
   Parent parent = FXMLLoader.load(getClass().getResource("/View/MainPage.fxml"));
   Scene scene = new Scene(parent);
   Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
   stage.setScene(scene);
   stage.show();
 }

  /**
   * Initializes the Table View of all Appointments.
   * @param url
   * @param resourceBundle
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      appointmentsTable.setItems(AppointmentsAccess.getAllAppointments());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    appointmentID.setCellValueFactory(new PropertyValueFactory< Appointment,Integer>("appointmentId"));
    title.setCellValueFactory(new PropertyValueFactory< Appointment,String>("title"));
    description.setCellValueFactory(new PropertyValueFactory<Appointment,String >("description"));
    location.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
    contact.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contactID"));
    type.setCellValueFactory(new PropertyValueFactory<Appointment,String>("type"));
    startDateandTime.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("startDateandTime"));
    endDateandTime.setCellValueFactory(new PropertyValueFactory<Appointment,LocalDateTime>("EndDateandTime"));
    customerID.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("customerID"));
    userID.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("userID"));

  }
}
