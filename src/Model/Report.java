package Model;

public class Report {
    private String Months;
    private String type;
    private int Count;
    private String Country;

    public String getCountry() {
        return Country;
    }

    public void setMonths(String months) {
        Months = months;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCount(int count) {
        Count = count;
    }

    public Report(int count, String country) {
        Count = count;
        Country = country;
    }

    public void setCountry(String country) {
        Country = country;

    }

    public Report(String month, String type, int count) {
        Months = month;
        this.type = type;
        Count = count;
    }

    public Report(String country, int count) {
    this.Country = country;
    this.Count = count;
    }

    public String getMonths() {
        return Months;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return Count;
    }
}
