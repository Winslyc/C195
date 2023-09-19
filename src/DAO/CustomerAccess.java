package DAO;

import Helper.JDBC;
import Model.Customer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static void selectAllCustomers() throws  SQLException{
        String sql = "SELECT * FROM Customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerId= rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs. getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date LastUpdate = rs.getDate("Last_Update");
            String LastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionId = rs. getInt("Division_ID");
            Customer.Customers.add(new Customer(customerId, customerName, address, postalCode, phone, createDate,createdBy,LastUpdate,LastUpdatedBy,divisionId));

        }


    }

}
