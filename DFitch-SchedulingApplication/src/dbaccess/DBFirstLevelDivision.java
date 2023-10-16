package dbaccess;

import helper.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: C195-Scheduler
 * Package: package dbaccess;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class has the string sql statements specific to the divisions table
 */
public class DBFirstLevelDivision {
    public static int divisionId = 0;

    /**
     * String Sql for getting Division from country.
     * @param country
     * @return
     * @throws SQLException
     */
    public static ObservableList getDivisionFromCountry(String country) throws SQLException {
        ObservableList currentDivision = FXCollections.observableArrayList();
        String sql = "SELECT first_level_divisions.Division ,countries.Country \n" +
                "FROM first_level_divisions \n" +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID \n" +
                "WHERE countries.Country = '" + country + "'";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while(rs.next()){
            currentDivision.add(new String(rs.getString("Division")));
        }
        return currentDivision;
    }

    /**
     * String sql to get all division names.
     * @return
     * @throws SQLException
     */
    public static ObservableList getAllRecords() throws SQLException {
        ObservableList allRecords = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        allRecords.removeAll();
        while(rs.next()){
            allRecords.add(new String(rs.getString("Division")));
        }
        return allRecords;
    }

    /**
     * String sql to get division id.
     * @param Division
     * @return
     * @throws SQLException
     */
    public static int getDivisionID(String Division) throws SQLException {
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = '" + Division + "'";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while (rs.next()){
            divisionId = rs.getInt("Division_ID");
        }
        return divisionId;
    }

}
