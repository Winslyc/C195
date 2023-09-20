package Model;

public class Divisions {

    private String divisionName;
    private int divisionId;
    public int countryID;

    public String getDivisionName() {
        return divisionName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public Divisions(String divisionName, int divisionId, int countryID) {
        this.divisionName = divisionName;
        this.divisionId = divisionId;
        this.countryID = countryID;
    }
}

