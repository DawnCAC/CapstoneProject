package model;

/**
 * Project: C195-Scheduler
 * Package: package model;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * Contact Class setting up getters, setters, and constructors
 */
public class Contact {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    /**
     * Main constructor
     * @param contact_ID
     * @param contact_Name
     * @param email
     */
    public Contact(int contact_ID, String contact_Name, String email) {
        Contact_ID = contact_ID;
        Contact_Name = contact_Name;
        Email = email;
    }

    /**
     *
     * @return Contact_ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     *
     * @param contact_ID
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**
     *
     * @return Contact_Name
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     *
     * @param contact_Name
     */
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /**
     *
     * @return Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        Email = email;
    }
}
