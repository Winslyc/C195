package Main;

import DAO.CustomerAccess;
import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch(args);
        int rows =CustomerAccess.insert("Winsly Cyrius", "RandoAddress32", "2151", "4049487338");
        System.out.println(rows);
    JDBC.closeConnection();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../View/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        primaryStage.setTitle("Client Schedules");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
