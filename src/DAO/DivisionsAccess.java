package DAO;

import Helper.JDBC;
import Model.Country;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
          System.out.println(firstLevelDivision.getDivisionName());
        }
        return firstLevelDivisionsObservableList;
    }
    public static ObservableList<Country> getAllCountries() throws  SQLException{
        ObservableList<Country> countriesList  = FXCollections.observableArrayList();
        String sql ="Select * from Countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            countriesList.add(new Country(rs.getString("Country"), rs.getInt("Country_ID")));
        }
        return countriesList;
    }


}


