package Helper;

import javafx.scene.control.Alert;

public abstract class Alerter {
    public static void displayErrorAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}