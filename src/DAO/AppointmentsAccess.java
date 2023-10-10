package DAO;

import Helper.JDBC;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class AppointmentsAccess {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList <Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int appointmentID= rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime startDateTime =rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTime =rs.getTimestamp("End").toLocalDateTime();
            int customerID =rs.getInt("Contact_ID");
            int userID= rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
allAppointments.add(new Appointment(appointmentID,title,description,location,type, startDateTime,endDateTime,
        customerID,userID,contactID));
        }
    return allAppointments;
    }

}
