package Model;

public class Country {
    String CountryName;
    int CountryId;

    public Country(String countryName, int countryId) {
        CountryName = countryName;
        CountryId = countryId;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }
}
