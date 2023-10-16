package dbaccess;

import helper.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;
import model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Project: C195-Scheduler
 * Package: package dbaccess;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class has the string sql statements specific to the Schedule table
 */
public class DBAppointment {
    public static int nextId = 0;
    public static String currentUser = null;

    /**
     * String sql for getting all appointments.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppts() throws SQLException {
        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID\n" +
                "FROM appointments\n" +
                "LEFT JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID\n";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        allAppts.removeAll();
        while (rs.next()){
            allAppts.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Contact_Name"), rs.getString("Type"), rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"), rs.getInt("User_ID")));
        }
        return allAppts;
    }

    /**
     * Sorts appointments based on the monthly or weekly radio buttons on the schedule.
     * @param start
     * @param end
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getSortAppts(LocalDate start, LocalDate end) throws SQLException {
        ObservableList<Appointment> sortAppts = FXCollections.observableArrayList();
        String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID\n" +
                "FROM appointments\n" +
                "LEFT JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID\n" +
                "WHERE Start >= '" + start + "' and Start < '" + end +"'\n" +
                "ORDER BY Start";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        sortAppts.removeAll();
        while (rs.next()){
            sortAppts.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Contact_Name"), rs.getString("Type"), rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"), rs.getInt("User_ID")));
        }
        return sortAppts;
    }

    /**
     * String sql for deleting appointments.
     * @param Id
     */
    public static void deleteAppt(int Id, String type){
        String sql = "DELETE FROM appointments WHERE Appointment_ID = '" + Id + "'";
        Query.makeQuery(sql);
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setTitle("Appointment Cancelled");
        alert.setContentText("Appointment Id: " + Id + ", Appointment Type: " + type);
        alert.showAndWait();
        return;
    }

    /**
     * String sql for creating a new appointment.
     * @param title
     * @param description
     * @param location
     * @param contact
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     * @throws SQLException
     */
    public static void newAppt (String title, String description, String location, String contact, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID) throws SQLException {
        int apptID = getNextId();
        int contact_ID = getContactID(contact);
        ZonedDateTime zonedStart = ZonedDateTime.of(start, ZoneId.systemDefault());
        ZonedDateTime zonedEnd = ZonedDateTime.of(end, ZoneId.systemDefault());
        ZonedDateTime utcStart = zonedStart.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime utcEnd = zonedEnd.withZoneSameInstant(ZoneId.of("UTC"));
        Timestamp dbStart = Timestamp.valueOf(utcStart.toLocalDateTime());
        Timestamp dbEnd = Timestamp.valueOf(utcEnd.toLocalDateTime());
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
                "VALUES ('" + apptID + "','" + title + "','" + description + "','" + location + "','" + type + "','" + dbStart + "','" + dbEnd + "', NOW(), '" + currentUser + "', NOW(), '" + currentUser + "','" + customerID + "','" + userID + "','" + contact_ID + "')";
        Query.makeQuery(sql);
    }

    /**
     * String sql for modifying appointments.
     * @param id
     * @param title
     * @param description
     * @param location
     * @param contact
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     * @throws SQLException
     */
    public static void modifyAppt (int id, String title, String description, String location, String contact, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID) throws SQLException {
        int contact_ID = getContactID(contact);
        ZonedDateTime zonedStart = ZonedDateTime.of(start, ZoneId.systemDefault());
        ZonedDateTime zonedEnd = ZonedDateTime.of(end, ZoneId.systemDefault());
        ZonedDateTime utcStart = zonedStart.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime utcEnd = zonedEnd.withZoneSameInstant(ZoneId.of("UTC"));
        Timestamp dbStart = Timestamp.valueOf(utcStart.toLocalDateTime());
        Timestamp dbEnd = Timestamp.valueOf(utcEnd.toLocalDateTime());
        String sql = "UPDATE appointments \n" +
                "SET Appointment_ID = '" + id + "', Title = '" + title + "', Description = '" + description + "', Location = \n'"
                + location + "', Type = '" + type + "', Start = '" + dbStart + "', End = '" + dbEnd + "', Last_Update = NOW(), \n"
                + "Last_Updated_By = '" + currentUser + "', Customer_ID = '" + customerID + "', User_ID = '" + userID + "', Contact_ID = '" + contact_ID + "\n'"
                + "WHERE Appointment_ID = '" + id + "'";
        Query.makeQuery(sql);
    }

    /**
     * String sql for retrieving contact_Id from a contact.
     * @param contact
     * @return
     * @throws SQLException
     */
    private static int getContactID(String contact) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_Name = '" + contact + "'";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        int contactId = 0;
        while(rs.next()) {
            contactId = rs.getInt("Contact_ID");
        }
        return contactId;
    }

    /**
     * String sql to get the next available Id number.
     * @return
     * @throws SQLException
     */
    public static int getNextId() throws SQLException {
        String sql = "SELECT MAX(Appointment_ID) AS Appointment_ID FROM appointments";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while(rs.next()){
            nextId = rs.getInt("Appointment_ID");
            nextId ++;
        }
        return nextId;
    }

    /**
     * String SQL that checks if appointments overlap
     * @param Customer_ID
     * @param myStart
     * @param myEnd
     * @return
     * @throws SQLException
     */
    public static int overlapAppt (int Customer_ID, String Appt_Id, LocalDateTime myStart, LocalDateTime myEnd) throws SQLException {
        ZonedDateTime zonedStart = ZonedDateTime.of(myStart, ZoneId.systemDefault());
        ZonedDateTime zonedEnd = ZonedDateTime.of(myEnd, ZoneId.systemDefault());
        ZonedDateTime utcStart = zonedStart.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime utcEnd = zonedEnd.withZoneSameInstant(ZoneId.of("UTC"));
        Timestamp dbStart = Timestamp.valueOf(utcStart.toLocalDateTime());
        Timestamp dbEnd = Timestamp.valueOf(utcEnd.toLocalDateTime());

        String sql = "SELECT Appointment_ID FROM appointments \n" +
                "WHERE Customer_ID = " + Customer_ID + "\n" +
                "AND '" + dbEnd + "' > Start AND '" + dbStart + "' < End";
        if (Appt_Id != null){
            sql = sql + " AND Appointment_ID != " + Appt_Id;
        }
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        int count = 0;
        while(rs.next()){
            count++;
        }
        return count;
    }

    /**
     * Gets the appointments that are within 15min of the user logging in.
     * @param logInTime
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getApptAlert(LocalDateTime logInTime) throws SQLException {
        ObservableList<Appointment> apptAlert = FXCollections.observableArrayList();
        LocalDateTime logInPlus = logInTime.plusMinutes(16);
        ZonedDateTime zonedStart = ZonedDateTime.of(logInTime, ZoneId.systemDefault());
        ZonedDateTime zonedEnd = ZonedDateTime.of(logInPlus, ZoneId.systemDefault());
        ZonedDateTime utcStart = zonedStart.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime utcEnd = zonedEnd.withZoneSameInstant(ZoneId.of("UTC"));
        Timestamp dbLogIn = Timestamp.valueOf(utcStart.toLocalDateTime());
        Timestamp dbLogInPlus = Timestamp.valueOf(utcEnd.toLocalDateTime());
        String sql = "SELECT * FROM appointments WHERE Start > '" + dbLogIn + "' AND Start < '" + dbLogInPlus + "'";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while (rs.next()){
            apptAlert.add(new Appointment(rs.getInt("Appointment_ID"), rs.getTimestamp("Start").toLocalDateTime()));
        }
        return apptAlert;
    }
    // Below are the methods for the Reports

    /**
     * Gets appointment counts by type
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getApptByType() throws SQLException {
        ObservableList<Appointment> apptByType = FXCollections.observableArrayList();
        String sql = "SELECT Type, COUNT(Type) AS Total FROM appointments GROUP BY Type";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while(rs.next()){
            apptByType.add(new Appointment(rs.getString("Type"), rs.getInt("Total")));
        }
        return apptByType;
    }

    /**
     * Gets appointment counts by month
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getApptByMonth() throws SQLException {
        ObservableList<Appointment> apptByMonth = FXCollections.observableArrayList();
        String sql = "SELECT CONCAT(EXTRACT(MONTH FROM Start), \"-\", EXTRACT(YEAR FROM Start)) AS Month, COUNT(Type) AS Total FROM appointments\n" +
                "GROUP BY Month";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while(rs.next()){
            apptByMonth.add(new Appointment(rs.getString("Month"), rs.getInt("Total")));
        }
        return apptByMonth;
    }

}
