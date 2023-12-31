package Main;

import DAO.CustomerAccess;
import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class Main extends Application {
    public static void main(String[] args) throws SQLException {
        Locale locale= Locale.getDefault();

        TimeZone obj = TimeZone.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("Language/NAT", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr") ||
                Locale.getDefault().getLanguage().equals("en")){
        }
        launch(args);
        JDBC.closeConnection();
   }

    /**
     * Starts the application client scheduling application.
     * @param primaryStage The Primary stage container that will hold the application view.
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../View/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330,250);
        primaryStage.setTitle("Client Schedules");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
