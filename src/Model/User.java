package Model;

import java.sql.Date;

public class User {
    /**
     * The Username field to store user.
     */
    private String Username;
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
}
