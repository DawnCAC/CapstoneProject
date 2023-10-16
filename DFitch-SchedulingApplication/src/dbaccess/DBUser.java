package dbaccess;

import helper.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: C195-Scheduler
 * Package: package dbaccess;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class has the string sql statements specific to the Users table
 */
public class DBUser {
    /**
     * Uses string sql to autheticate the username and password for logging in.
     * @param userName
     * @param password
     * @return
     * @throws SQLException
     */
    public static boolean authenticateUser(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = '" + userName + "' and Password = '" + password + "'";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        if (rs.next()){
            return true;
        }
        else{return false;}
    }

    /**
     * Gets the user id based on the give user name.
     * @param currentUser
     * @return
     * @throws SQLException
     */
    public static int getUserID(String currentUser) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = '" + currentUser + "'";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        int userID = 0;
        while (rs.next()){
            userID = rs.getInt("User_ID");
        }
        return userID;
    }
}
