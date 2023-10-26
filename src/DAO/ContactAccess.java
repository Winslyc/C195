package DAO;

import Helper.JDBC;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactAccess {
    /**
     * Returns a list of all Contacts
     * @return
     */
    public static ObservableList<Contact> getAllContacts(){
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM CONTACTS";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contact_id = rs.getInt("Contact_ID");
                String contact_Name = rs.getString("Contact_Name");
            String contact_Email = rs.getString("Email");
            allContacts.add( new Contact(contact_id, contact_Name, contact_Email));
            }
            return allContacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Returns Contact ID from the contact name that is passed in
     * @param contact
     * @return
     * @throws SQLException
     */
    public static int getContactID(String contact) throws SQLException {
        String sql = "SELECT * FROM CONTACTS WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, contact);
        ResultSet rs = ps.executeQuery();
        int contact_id = 0;
        while(rs.next()){
              contact_id = rs.getInt("Contact_ID");
        }
        return contact_id;
    }

    /**
     * Returns a Contacts name from a Contact Id that is passed in
     * @param contactID
     * @return
     * @throws SQLException
     */
    public static String getContactName(int contactID) throws SQLException {

        String sql = "SELECT * FROM CONTACTS WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();
       String contact_Name = "";
        while(rs.next()){
            contact_Name = rs.getString("Contact_Name");
        }
        return contact_Name;
    }
}

