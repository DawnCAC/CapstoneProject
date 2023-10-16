package helper;

import java.sql.Statement;
import java.sql.ResultSet;
import static helper.JDBC.connection;

/**
 * Project: C195-Scheduler
 * Package: package helper;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class holds the queries for the project
 */
public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    /**
     * Used to make the queries to the database
     * @param q
     */
    public static void makeQuery(String q){
        query = q;
        try{
            stmt = connection.createStatement();
            if (query.toLowerCase().startsWith("select"))
                result = stmt.executeQuery(q);
            if (query.toLowerCase().startsWith("delete")||query.toLowerCase().startsWith("insert")||query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ResultSet getResult() {
        return result;
    }
}
