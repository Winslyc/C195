package DAO;

import Helper.JDBC;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionsAccess {

    public static ObservableList<Divisions> getAllDivisions() throws SQLException{
        ObservableList<Divisions> firstLevelDivisionsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * from first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int country_ID = rs.getInt("COUNTRY_ID");
            Divisions firstLevelDivision = new Divisions(divisionName,divisionID,  country_ID);
            firstLevelDivisionsObservableList.add(firstLevelDivision);
        }
        return firstLevelDivisionsObservableList;
    }


}


