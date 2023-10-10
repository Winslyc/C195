package Model;

import java.time.LocalDateTime;

public class Appointment {
    private  int appointmentId;
    private String title;
    private String description;
    private String location;

    private String type;
    private LocalDateTime startDateandTime;
    private LocalDateTime endDateandTime;
    private int customerID;
    private int userID;
    private  int contactID;

    public Appointment(int appointmentID, String title,
                       String description, String location,
                       String type, LocalDateTime startDateandTime,
                       LocalDateTime endDateandTime, int customerID, int userID, int contactID) {
        this.appointmentId = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateandTime = startDateandTime;
        this.endDateandTime = endDateandTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStartDateandTime() {
        return startDateandTime;
    }

    public LocalDateTime getEndDateandTime() {
        return endDateandTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }

    public int getContactID() {
        return contactID;
    }

}
