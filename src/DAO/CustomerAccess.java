package DAO;

import Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class CustomerAccess {
    public static int insert(String Name, String Address, String Postal_Code, String Phone) throws SQLException {
        String sql = "INSERT INTO customers(Customer_name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        int rows = ps.executeUpdate();
        return rows;
    }
}
