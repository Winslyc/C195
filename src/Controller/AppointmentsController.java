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
  /**
   * The appointments table
   */
  @FXML
  TableView<Appointment> appointmentsTable;
  /**
   * The appointment Id Column
   */
  @FXML
  TableColumn<Appointment, Integer> appointmentID;
  /**
   * The Title column
   */
  @FXML
  TableColumn<Appointment, String> title;

  /**
   * The Description Column
   */
  @FXML
  TableColumn<Appointment, String> description;
  /**
   * The Location Column
   */
  @FXML
  TableColumn<Appointment, String> location;
  /**
   * The Contact Column
    */
  @FXML
  TableColumn<Appointment, String> contact;
  /**
   * The Type Column
   */
  @FXML
  TableColumn<Appointment, String> type;
  /**
   * The Start Date/Time Column
   */
  @FXML
  TableColumn<Appointment, LocalDateTime> startDateandTime;
  /**
   * The End Date/Time Column
    */
  @FXML
  TableColumn<Appointment, LocalDateTime> endDateandTime;
  /**
   * The Customer ID Column
   */
  @FXML
  TableColumn<Appointment, Integer> customerID;
  /**
   * The user Id Column
   */
  @FXML
  TableColumn<Appointment, Integer> userID;
  @FXML RadioButton monthRadioButton;
  @FXML RadioButton weekRadioButton;

  /**
   * Opens update Appointment Page
   * @param actionEvent
   * @throws IOException
   */
  public void onSubmitNew(ActionEvent actionEvent) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
    Scene scene = new Scene(parent);
    Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Deletes an appointment from the Database
   * @param actionEvent
   * @throws SQLException
   */
  public void onSubmitDelete(ActionEvent actionEvent) throws SQLException {
    Appointment selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
    if(AppointmentsAccess.deleteAppointment(selectedAppointment)){
      Alerter.displayAlert("Deletion Succesful", "You have deleted Appointment " + String.valueOf(selectedAppointment.getAppointmentId()) + " This was an " +
              selectedAppointment.getType() + " type of appointment. " , "Thank You.");
      appointmentsTable.setItems(AppointmentsAccess.getAllAppointments());
    }
    else{
      Alerter.displayErrorAlert("Delete Unsuccessful", "Please try again your deletion of Appointment " + selectedAppointment.getAppointmentId() + " was unsuccesful.");
    }


  }

  /**
   * Resets Radio Buttons to an unselected State
   * @param actionEvent
   * @throws SQLException
   */
  public void resetFilters(ActionEvent actionEvent) throws SQLException {
appointmentsTable.setItems(AppointmentsAccess.getAllAppointments());
monthRadioButton.setSelected(false);
weekRadioButton.setSelected(false);
  }
  //TODO Fix the Radiobuttons, so that filter by week can be selected after filter by month.

  /**
   * Filters by month all upcoming appointments
   * @param actionEvent
   * @throws SQLException
   */
  public void onSelectMonthly(ActionEvent actionEvent) throws SQLException {
        weekRadioButton.setSelected(false);
        appointmentsTable.setItems(AppointmentsAccess.getAppointmentsByMonth(LocalDateTime.now().toLocalDate().atStartOfDay()));
        if(AppointmentsAccess.getAppointmentsByMonth(LocalDateTime.now().toLocalDate().atStartOfDay()).isEmpty()){
          Alerter.displayAlert("No Appointments", "There are no appointments scheduled in the next 30 days", "If you would like to schedule an appointment please proceed and click add to schedule");
        }
  }

  /**
   * Filters By Week all upcoming appointments.
   * @param actionEvent
   * @throws SQLException
   */
  public void onSelectWeekly(ActionEvent actionEvent) throws SQLException{
    monthRadioButton.setSelected(false);
    appointmentsTable.setItems(AppointmentsAccess.getAppointmentsByWeek(LocalDateTime.now().toLocalDate().atStartOfDay()));
    if(AppointmentsAccess.getAppointmentsByWeek(LocalDateTime.now().toLocalDate().atStartOfDay()).isEmpty()) {
      Alerter.displayAlert("No Appointments", "There are no appointments scheduled in the next 7 days", "If you would like to schedule an appointment please proceed and click add to schedule");
    }
    }

  /**
   * Opens the Edit Appointment Page
   * @param actionEvent
   * @throws IOException
   */
  public void onSubmitEdit(ActionEvent actionEvent) throws  IOException{
    UpdateAppointmentController.selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
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

  /**
   *  Returns you to the previous page.
   * @param actionEvent
   * @throws IOException
   */
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
