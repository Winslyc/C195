package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML
    Button customerTab;

    public void onSubmitCustomers(ActionEvent actionEvent)  throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        System.out.println("Pass");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}