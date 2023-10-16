package model;

//getters/setters and such

/**
 * Project: C195-Scheduler
 * Package: package model;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * Customer Class setting up getters, setters, and constructors
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String division;
    private String country;
    private String postalCode;
    private String phone;

    /**
     * Main constructor
     * @param id
     * @param name
     * @param address
     * @param division
     * @param country
     * @param postalCode
     * @param phone
     */
    public Customer(int id, String name, String address, String division, String country, String postalCode, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.division = division;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    /**
     * Empty constructor
     */
    public Customer() {
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     *
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
