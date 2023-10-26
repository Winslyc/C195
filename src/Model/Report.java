package Model;

 public class Report {

    /** The name of the month. */
    private String Months;

    /** The type of the report. */
    private String type;

    /** The count associated with the report. */
    private int Count;

    /** The country associated with the report. */
    private String Country;

    /**
     * Get the name of the month.
     * @return The name of the month.
     */
    public String getMonths() {
        return Months;
    }

    /**
     * Get the type of the report.
     * @return The type of the report.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the count associated with the report.
     * @return The count associated with the report.
     */
    public int getCount() {
        return Count;
    }

    /**
     * Get the country associated with the report.
     * @return The country associated with the report.
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Set the name of the month.
     * @param months The name of the month to set.
     */
    public void setMonths(String months) {
        Months = months;
    }

    /**
     * Set the type of the report.
     * @param type The type of the report to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set the count associated with the report.
     * @param count The count associated with the report to set.
     */
    public void setCount(int count) {
        Count = count;
    }

    /**
     * Set the country associated with the report.
     * @param country The country associated with the report to set.
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * Creates a report with the specified month, type, and count.
     * @param month The name of the month.
     * @param type The type of the report.
     * @param count The count associated with the report.
     */
    public Report(String month, String type, int count) {
        Months = month;
        this.type = type;
        Count = count;
    }

    /**
     * Creates a report with the specified country and count.
     * @param country The country associated with the report.
     * @param count The count associated with the report.
     */
    public Report(String country, int count) {
        Country = country;
        Count = count;
    }
}