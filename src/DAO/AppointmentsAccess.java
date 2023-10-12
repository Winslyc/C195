package DAO;

import Helper.JDBC;
import Helper.TimeUtil;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    public static void addNewAppointment(Appointment newAppointment) throws SQLException {
        String sql = "INSERT INTO Appointments(Appointment_ID, Title, " +
                "Description, Location, Type, Start, End, Create_Date, Created_By," +
                " Last_Update, Last_Updated_By,Customer_ID, User_ID, Contact_ID) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,newAppointment.getAppointmentId());
        ps.setString(2, newAppointment.getTitle());
        ps.setString(3,newAppointment.getDescription());
        ps.setString(4, newAppointment.getLocation());
        ps.setString(5, newAppointment.getType());
        ps.setTimestamp(6, Timestamp.valueOf(newAppointment.getStartDateandTime()));
        ps.setTimestamp(7, Timestamp.valueOf(newAppointment.getEndDateandTime()));
        ps.setTimestamp(8,Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(9, UserAccess.getCurrentUser().getUsername());
        ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(11, UserAccess.currentUser.getUsername());
        ps.setInt(12,newAppointment.getCustomerID());
        ps.setInt(13, newAppointment.getUserID());
        ps.setInt(14,newAppointment.getContactID());
        ps.executeUpdate();

    }
}
