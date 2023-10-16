package dbaccess;

import helper.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: C195-Scheduler
 * Package: package dbaccess;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class has the string sql statements specific to the contacts table
 */
public class DBContact {
    /**
     * String sql for retrieving all contacts.
     * @return
     * @throws SQLException
     */
    public static ObservableList getAllContacts() throws SQLException {
        ObservableList allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        allContacts.removeAll();
        while(rs.next()){
            allContacts.add(rs.getString("Contact_Name"));
        }
        return allContacts;
    }
}
