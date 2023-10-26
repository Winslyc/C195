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
    /**
     * Returns a list of all Divisions.
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns a list of All Countries
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns a division from the Divison ID
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static Divisions getDivision(int divisionID) throws SQLException {
        String sql = "Select * from first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        Divisions divisions = null;
        while(rs.next()) {
            divisions = new Divisions(rs.getString("Division"), rs.getInt("Division_ID"), rs.getInt("Country_ID"));
        }
    return divisions;
}

}


