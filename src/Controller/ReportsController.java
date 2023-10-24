package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javax.swing.*;

public class ReportsController {
    public static String selectedReport;
    public void onSelectReport(ActionEvent actionEvent){
    Button button = (Button) actionEvent.getSource();
    if(button.lookup("#report1") != null){
        System.out.println("report1");
    } else if(button.lookup("#report2") != null){
        System.out.println("report2");
    }else if(button.lookup("#report3") != null){
        System.out.println("report3");
    }
    }
}
