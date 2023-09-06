package Helper;

import javafx.scene.control.Alert;

public abstract class Alerter {
    /**
     * Displays Alerts application wide
     * @param title sets the title for the error alert.
     * @param contentText sets the content for the error alert.
     */
    public static void displayErrorAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}