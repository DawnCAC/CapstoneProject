package model;

/**
 * Project: C195-Scheduler
 * Package: package model;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * Country Class setting up getters, setters, and constructors
 */
public class Country {
    private String Country;
    private int CountryId;
    private int Total;

    /**
     * Constructor for the total appointments by countries report
     * @param country
     * @param total
     */
    public Country(String country, int total) {
        Country = country;
        Total = total;
    }

    /**
     *
     * @return country
     */
    public String getCountry() {
        return Country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     *
     * @return countryId
     */
    public int getCountryId() {
        return CountryId;
    }

    /**
     *
     * @param countryId
     */
    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    /**
     *
     * @return Total
     */
    public int getTotal() {
        return Total;
    }

    /**
     *
     * @param total
     */
    public void setTotal(int total) {
        Total = total;
    }
}
