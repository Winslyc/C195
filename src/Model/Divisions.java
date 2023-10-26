package Model;

public class Divisions {

    /** The name of the division. */
    private String divisionName;

    /** The unique ID of the division. */
    private int divisionId;

    /** The ID of the country to which this division belongs. */
    public int countryID;

    /**
     * Get the name of the division.
     * @return The division name.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Get the unique ID of the division.
     * @return The division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Get the ID of the country to which this division belongs.
     * @return The country ID.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Set the name of the division.
     * @param divisionName The division name to set.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Set the unique ID of the division.
     * @param divisionId The division ID to set.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Set the ID of the country to which this division belongs.
     * @param countryID The country ID to set.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Creates a new division with the specified details.
     * @param divisionName The name of the division.
     * @param divisionId The unique ID of the division.
     * @param countryID The ID of the country to which this division belongs.
     */
    public Divisions(String divisionName, int divisionId, int countryID) {
        this.divisionName = divisionName;
        this.divisionId = divisionId;
        this.countryID = countryID;
    }
}