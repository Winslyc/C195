package DAO;

import Helper.Alerter;
import Helper.JDBC;
import Model.Customer;
import Model.Divisions;
import Model.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public abstract class CustomerAccess {
   /* public static int insert(String Name, String Address, String Postal_Code, String Phone) throws SQLException {
        String sql = "INSERT INTO customers(Customer_name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);

        int rows = ps.executeUpdate();

        return rows;
    }    */

    /**
     * @return A list of all Customers
     * @throws SQLException
     */
    public static ObservableList selectAllCustomers() throws  SQLException{
        String sql = "SELECT * FROM Customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList <Customer> allCustomers = FXCollections.observableArrayList();
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
            ObservableList<Divisions> allTheDivisions = FXCollections.observableArrayList();
           Divisions divisions = DivisionsAccess.getDivision(divisionId);
            allCustomers.add(new Customer(customerId, customerName,
                    address, postalCode, phone, createDate,createdBy
                    ,LastUpdate,LastUpdatedBy,divisionId, divisions));

        }


        return allCustomers;
    }

    /**
     * Removes a Customer from the Database
     * @param newCustomer
     * @throws SQLException
     */
    public static void deleteCustomer(Customer newCustomer) throws SQLException{
        String sql = "DELETE FROM CUSTOMERS WHERE(Customer_ID = ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, newCustomer.getCustomerId());
        ps.executeUpdate();


    }

    /**
     * Adds a Customer to the database
     * @param newCustomer
     * @throws SQLException
     */
    public static void addcustomer(Customer newCustomer) throws SQLException {
        String sql = "INSERT INTO customers(Customer_ID, Customer_name, Address, Postal_Code, Phone" + "" +
                ",Create_Date, Created_By,Last_Update,Last_Updated_By,Division_ID) VALUES(?, ?, ?, ?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, newCustomer.getCustomerId());
        ps.setString(2,newCustomer.getCustomerName());
        ps.setString(3, newCustomer.getAddress());
        ps.setString(4, newCustomer.getPostalCode());
        ps.setString(5, newCustomer.getPhoneNumber());
        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(7, UserAccess.currentUser.getUsername());
        ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(9, UserAccess.getCurrentUser().getUsername());
        ps.setInt(10,newCustomer.getDivision());
        ps.executeUpdate();
    }

    /**
     * Updates Customers in the Database
     * @param id
     * @param updatedCustomer
     * @throws SQLException
     */
    public static void updateCustomer(int id, Customer updatedCustomer) throws SQLException {
        String sql = "UPDATE customers " +
                "SET Customer_ID = ?, Customer_Name = ?, Address = ?, Postal_Code= ?, Phone = ?, Last_Update = ?,  Last_Updated_By = ?, Division_ID = ? " +
                "WHERE Customer_ID = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, updatedCustomer.getCustomerName());
         ps.setString(3, updatedCustomer.getAddress());
        ps.setString(4,updatedCustomer.getPostalCode());
        ps.setString(5,updatedCustomer.getPhoneNumber());
        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(7, UserAccess.getCurrentUser().getUsername());
        ps.setInt(8, updatedCustomer.getDivision());
        ps.setInt(9, id);
        ps.executeUpdate();
    }

    /**
     * Returns a list of Total Customers By country
     * @return
     * @throws SQLException
     */
    public static ObservableList getTotalCustomersByCountry() throws SQLException {
        String sql ="Select Country, Count(Customer_ID) as Count \n" +
                "FROM countries \n" +
                "JOIN first_level_divisions as FLD on countries.Country_ID = FLD.Country_ID\n" +
                "JOIN customers as Customer on Customer.Division_ID = FLD.Division_ID\n" +
                "GROUP BY Country";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList list = FXCollections.observableArrayList();
        while(rs.next()) {
            String Country = rs.getString("Country");
            int Count = rs.getInt("Count");
            list.add(new Report(Country, Count));
        }
        return  list;
        }
}
