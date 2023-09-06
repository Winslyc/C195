package DAO;

import Helper.JDBC;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAccess {
    /**
     * The Current USer application wide.
     */
    public static User currentUser;
    /**
     * Username of the current User.
     */
    private  String username;
    /**
     * @return current logged in user
     */
    public static User getCurrentUser(){
        return currentUser;
    }
    /**
     * Boolean login checks user name and password from database
     * @return true if login is succesful
     */
    public static Boolean login(String userName, String password) throws SQLException {
       String sql = "SELECT * FROM USERS WHERE User_Name = ? AND Password = ?";
       PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ps.setString(1, userName);
       ps.setString(2,password);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){

          currentUser = new User();
          currentUser.setUsername(rs.getString("User_Name"));
          System.out.println(currentUser.getUsername());
           return true;
       }
       return false;
    }

}
