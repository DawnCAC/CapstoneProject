package model;

import java.time.LocalDateTime;

/**
 * Project: C195-Scheduler
 * Package: package model;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * Appointment Class setting up getters, setters, and constructors
 */
public class Appointment {
    private int ApptID;
    private String Title;
    private String Description;
    private String Location;
    private String Contact;
    private String Type;
    private  LocalDateTime Start;
    private  LocalDateTime End;
    private int CustomerID;
    private int UserID;
    private int Count;


    /**
     * Main constructor
     * @param apptID
     * @param title
     * @param description
     * @param location
     * @param contact
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     */
    public Appointment(int apptID, String title, String description, String location, String contact, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID) {
        ApptID = apptID;
        Title = title;
        Description = description;
        Location = location;
        Contact = contact;
        Type = type;
        Start = start;
        End = end;
        CustomerID = customerID;
        UserID = userID;
    }

    /**
     * Constructor for int and LocalDateTime types
     * @param apptID
     * @param start
     */
    public Appointment(int apptID, LocalDateTime start) {
        ApptID = apptID;
        Start = start;
    }

    /**
     * Constructor for String and int types
     * @param type
     * @param count
     */
    public Appointment(String type, int count) {
        Type = type;
        Count = count;

    }

    /**
     * Constructor for contact schedule
     * @param appointment_id
     * @param title
     * @param description
     * @param type
     * @param start
     * @param end
     * @param customer_id
     */
    public Appointment(int appointment_id, String title, String description, String type, LocalDateTime start, LocalDateTime end, int customer_id) {
        ApptID = appointment_id;
        Title = title;
        Description = description;
        Type = type;
        Start = start;
        End = end;
        CustomerID = customer_id;

    }

    /**
     *
     * @return ApptID
     */
    public int getApptID() {
        return ApptID;
    }

    /**
     *
     * @param apptID
     */
    public void setApptID(int apptID) {
        ApptID = apptID;
    }

    /**
     *
     * @return Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     *
     * @return Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     *
     * @return Location
     */
    public String getLocation() {
        return Location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     *
     * @return Contact
     */
    public String getContact() {
        return Contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        Contact = contact;
    }

    /**
     *
     * @return Type
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     *
     * @return Start
     */
    public  LocalDateTime getStart() {
        return Start;
    }

    /**
     *
     * @param start
     */
    public void setStart( LocalDateTime start) {
        Start = start;
    }

    /**
     *
     * @return End
     */
    public  LocalDateTime getEnd() {
        return End;
    }

    /**
     *
     * @param end
     */
    public void setEnd(LocalDateTime end) {
        End = end;
    }

    /**
     *
     * @return CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     *
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    /**
     *
     * @return UserID
     */
    public int getUserID() {
        return UserID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        UserID = userID;
    }

    /**
     *
     * @return Count
     */
    public int getCount() {
        return Count;
    }

    /**
     *
     * @param count
     */
    public void setCount(int count) {
        Count = count;
    }
}
