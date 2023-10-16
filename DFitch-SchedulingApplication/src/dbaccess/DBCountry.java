package dbaccess;

import helper.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: C195-Scheduler
 * Package: package dbaccess;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class has the string sql statements specific to the countries table
 */
public class DBCountry {
    /**
     * String sql for retrieving all countries.
     * @return
     * @throws SQLException
     */
    public static ObservableList getAllRecords() throws SQLException {
        ObservableList allRecords = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        allRecords.removeAll();
        while(rs.next()){
            allRecords.add(new String(rs.getString("Country")));
        }
        return allRecords;
    }
    /**
     * Gets appointment counts by country
     * @return
     * @throws SQLException
     */
    public static ObservableList<Country> getApptByCountry() throws SQLException {
        ObservableList<Country> apptByCountry = FXCollections.observableArrayList();
        String sql = "SELECT countries.Country, COUNT(appointments.Appointment_ID) AS Total \n" +
                "FROM customers \n" +
                "LEFT JOIN appointments ON customers.Customer_ID = appointments.Customer_ID \n" +
                "LEFT JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID \n" +
                "LEFT JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID\n" +
                "GROUP BY Country ORDER BY Country";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while(rs.next()){
            apptByCountry.add(new Country(rs.getString("Country"), rs.getInt("Total")));
        }
        return apptByCountry;
    }
}
