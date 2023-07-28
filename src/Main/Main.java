package Main;

import DAO.CustomerAccess;
import DAO.FruitsQuery;
import Helper.Alerter;
import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.TimeZone;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
        TimeZone obj = TimeZone.getDefault();
        System.out.println(obj.getDisplayName());
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../View/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        primaryStage.setTitle("Client Schedules");
        primaryStage.setScene(scene);
        primaryStage.show();
        JDBC.openConnection();
    }
}
