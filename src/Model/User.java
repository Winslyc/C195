package Model;

import java.sql.Date;
import java.time.ZoneId;
public class User {

    /** The username of the user. */
    private String Username;

    /** The time zone of the user. */
    private ZoneId userZoneID;

    /** The unique user ID. */
    private int userID;

    /**
     * Creates an empty user instance.
     */
    public User() {
        // Constructor implementation here...
    }

    /**
     * Get the username of the user.
     * @return The username of the user.
     */
    public String getUsername() {
        return Username;
    }

    /**
     * Set the username of the user.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        Username = username;
    }

    /**
     * Get the time zone of the user.
     * @return The time zone of the user.
     */
    public ZoneId getUserZoneID() {
        return userZoneID;
    }

    /**
     * Set the time zone of the user.
     * @param userZoneID The time zone to set.
     */
    public void setUserZoneID(ZoneId userZoneID) {
        this.userZoneID = userZoneID;
    }

    /**
     * Get the unique user ID.
     * @return The unique user ID.
     */
    public int getUserId() {
        return userID;
    }

    /**
     * Creates a user with the specified username, time zone, and user ID.
     * @param username The username of the user.
     * @param userZoneID The time zone of the user.
     * @param Id The unique user ID.
     */
    public User(String username, ZoneId userZoneID, int Id) {
        Username = username;
        this.userZoneID = userZoneID;
        userID = Id;
    }
}