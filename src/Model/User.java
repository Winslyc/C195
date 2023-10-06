package Model;

import java.sql.Date;
import java.time.ZoneId;

public class User {
    /**
     * The Username field to store user.
     */

    private String Username;
    private ZoneId userZoneID;
    private int userID;
    public User() {

    }
    /**
     *
     * @return Returns the username for the current logged in user.
     */
    public String getUsername() {
        return Username;
    }

    /**
     *  set's the username for the current user.
     * @param username sets the username for the current user.
     */
    public void setUsername(String username) {
        Username = username;
    }

    public User(String username, ZoneId userZoneID,int Id) {
        Username = username;
        this.userZoneID = userZoneID;
    }

    public ZoneId getUserZoneID() {
        return userZoneID;
    }

    public void setUserZoneID(ZoneId userZoneID) {
        this.userZoneID = userZoneID;
    }

    public int getUserId() {
        return userID;
    }
}

