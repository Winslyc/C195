package DAO;

import Helper.JDBC;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAccess {
    public static User currentUser;

    public static User getCurrentUser(){
        return currentUser;
    }

    /**
     * Boolean login checks user name and password from database
     * @return true if login is succesful
     */
    public static Boolean login(String userName, String password) throws SQLException {
       String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
       PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ps.setString(1, userName);
       ps.setString(2,password);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           return true;
       }
       return false;
    }
        public static void select() throws SQLException{
        String sql = "SELECT * FROM USERS WHERE Username = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
    }
}
