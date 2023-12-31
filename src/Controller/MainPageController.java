package Controller;

import DAO.CustomerAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    /**
     * The Button to Open Customer page.
     */
    @FXML
    Button customerTab;

    /**
     * Opens the Customer Page
     * @param actionEvent
     * @throws IOException
     */
    public void onSubmitCustomers(ActionEvent actionEvent)  throws IOException{
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Customer.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Opens the Appointment Schedule
     * @param actionEvent
     * @throws IOException
     */
    public void onSubmitSchedule(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     *  Opens the Reports Section of the program
     * @param actionEvent
     * @throws IOException
     */
    public void onSubmitReports(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Reports.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the Main Page
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CustomerAccess.selectAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}