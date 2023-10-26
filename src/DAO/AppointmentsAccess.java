package DAO;

import Helper.JDBC;
import Model.Appointment;
import Model.Customer;
import Model.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.sql.*;
import java.time.LocalDateTime;

public abstract class AppointmentsAccess {
    /**
     * Returns a list of all Appointments.
     * @return
     * @throws SQLException
     */
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
            LocalDateTime startDateTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTime =rs.getTimestamp("End").toLocalDateTime();
            int customerID =rs.getInt("Contact_ID");
            int userID= rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
allAppointments.add(new Appointment(appointmentID,title,description,location,type, startDateTime,endDateTime,
        customerID,userID,contactID));
        }
    return allAppointments;
    }

    /**
     * Adds a new appointment to the Database
     * @param newAppointment
     * @throws SQLException
     */
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

    /**
     * Updates an appointment in the database
     * @param newAppointment
     * @throws SQLException
     */
    public static void updateAppointment(Appointment newAppointment) throws SQLException {
        String sql = "UPDATE Appointments" +
                " SET Appointment_ID = ?, Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, " +
                "Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?" +
                " WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, newAppointment.getAppointmentId());
        ps.setString(2, newAppointment.getTitle());
        ps.setString(3,newAppointment.getDescription());
        ps.setString(4, newAppointment.getLocation());
        ps.setString(5, newAppointment.getType());
        ps.setTimestamp(6,Timestamp.valueOf(newAppointment.getStartDateandTime()));
        ps.setTimestamp(7,Timestamp.valueOf(newAppointment.getEndDateandTime()));
        ps.setTimestamp(8,Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(9, UserAccess.getCurrentUser().getUsername());
        ps.setInt(10, newAppointment.getCustomerID());
        ps.setInt(11,newAppointment.getUserID());
        ps.setInt(12,newAppointment.getContactID());
        ps.setInt(13, newAppointment.getAppointmentId());
        ps.executeUpdate();
    }

    /**
     * Removes an appointment from the Database
     * @param newAppointment
     * @return
     */
    public static boolean deleteAppointment(Appointment newAppointment)  {
        try {
            String sql = "DELETE FROM Appointments WHERE(Appointment_ID = ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, newAppointment.getAppointmentId());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }

        return  true;
    }

    /**
     * Returns a List of Filtered Appointments from the current Date to the Next 30 Days
     * @param loginDateTime
     * @return
     * @throws SQLException
     */
    public static FilteredList<Appointment> getAppointmentsByMonth(LocalDateTime loginDateTime) throws SQLException {
        ObservableList<Appointment> monthAppointment = FXCollections.observableArrayList();
        monthAppointment =getAllAppointments();
        FilteredList<Appointment> filteredWeekAppts = new FilteredList<>(monthAppointment);
        filteredWeekAppts.setPredicate(appointment -> {
            LocalDateTime apptDate = appointment.getStartDateandTime();
            return((apptDate.isEqual(loginDateTime) || apptDate.isAfter(loginDateTime) && apptDate.isBefore(loginDateTime.plusDays(30))));
        });
        return filteredWeekAppts;

    }

    /**
     * Returns a List of Filtered Appointments from the current Date to the Next 7 Days
     * @param loginDateTime
     * @return
     * @throws SQLException
     */
    public static FilteredList<Appointment>  getAppointmentsByWeek(LocalDateTime loginDateTime) throws SQLException {
        ObservableList<Appointment> weekAppt = FXCollections.observableArrayList();
        weekAppt =getAllAppointments();
        FilteredList<Appointment> filteredWeekAppts = new FilteredList<>(weekAppt);
        filteredWeekAppts.setPredicate(appointment -> {
            LocalDateTime apptDate = appointment.getStartDateandTime();
            return((apptDate.isEqual(loginDateTime) || apptDate.isAfter(loginDateTime) && apptDate.isBefore(loginDateTime.plusDays(30))));
        });
        return filteredWeekAppts;
    }

    /**
     * Returns A list of Appointments For Each Contact
     * @param contactID
     * @return
     * @throws SQLException
     */
    public static ObservableList getAppointmentsByContact(int contactID) throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        getAllAppointments().forEach(i-> {
            if(i.getContactID()==contactID){
                list.add(i);
            }
        });
        return list;
    }

    /**
     * Returns the Amount of Appointments By type and Month in a list
     * @return
     * @throws SQLException
     */
    public static ObservableList getAppointmentByTypeMonth() throws SQLException {
       String sql = "Select monthname(Start) as Month, Type, count(Appointment_ID) as Count from appointments Group By Type, Start";
       PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       ObservableList list = FXCollections.observableArrayList();
       while (rs.next()){
           String Month = rs.getString("Month");
           String Type = rs.getString("Type");
           int count = rs.getInt("Count");
           list.add(new Report(Month,Type,count));
        }
       return list;
    }

    public static boolean CheckforCustomerAppointments(Customer toDelete) throws SQLException {
        String sql = "SELECT * From Appointments WHERE Customer_ID = ?";
        PreparedStatement ps =JDBC.connection.prepareStatement(sql);
        ps.setInt(1, toDelete.getCustomerId());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }
}
