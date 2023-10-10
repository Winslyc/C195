package DAO;

import Helper.JDBC;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactAccess {
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


    }

