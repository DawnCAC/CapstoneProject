package dbaccess;

//Static Methods to Access Data?

import helper.JDBC;
import helper.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: C195-Scheduler
 * Package: package dbaccess;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class has the string sql statements specific to the customer table
 */
public class DBCustomer {
    public static int nextId = 0;

    /**
     * String sql for getting all users from Database
     * @return returns all customers
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code,\n" +
                "customers.Phone, first_level_divisions.Division,countries.Country \n" +
                "FROM customers \n" +
                "LEFT JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID \n" +
                "LEFT JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        allCustomers.removeAll();
        while(rs.next()){
            allCustomers.add(new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"),
                    rs.getString("Address"), rs.getString("Division"), rs.getString("Country"),
                    rs.getString("Postal_Code"), rs.getString("Phone")));
        }
        return allCustomers;
    }

    /**
     * String sql for deleting a customer
     * @param Id
     */
    public static void deleteCustomer(int Id){
        String sql2 = "DELETE FROM appointments WHERE Customer_ID = '" + Id + "'";
        Query.makeQuery(sql2);
        String sql = "DELETE FROM customers WHERE Customer_ID = '" + Id + "'";
        Query.makeQuery(sql);
    }

    /**
     * String sql for creating a new customer
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param createdBy
     * @param updatedBy
     * @param divisionId
     * @throws SQLException
     */
    public static void newCustomer(String name, String address, String postalCode, String phone, String createdBy, String updatedBy, int divisionId) throws SQLException {
        int Customer_ID = getNextId();
        String Customer_Name = name;
        String Address = address;
        String Postal_Code = postalCode;
        String Phone = phone;
        String Created_By = createdBy;
        String Last_Updated_By = updatedBy;
        int Division_ID = divisionId;

        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) \n" +
                "VALUES ('" + Customer_ID + "','" + Customer_Name + "','" + Address + "','" + Postal_Code + "','" + Phone + "',NOW(),'" + Created_By + "',NOW(),'" + Last_Updated_By + "','" + Division_ID + "')";
        Query.makeQuery(sql);
    }

    /**
     * String sql to get the next available Id number.
     * @return
     * @throws SQLException
     */
    public static int getNextId() throws SQLException {
        String sql = "SELECT MAX(Customer_ID) AS Customer_ID FROM customers";
        Query.makeQuery(sql);
        ResultSet rs = Query.getResult();
        while(rs.next()){
            nextId = rs.getInt("Customer_ID");
            nextId ++;
        }
        return nextId;
    }

    /**
     * String sql for modifying a customer.
     * @param id
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param createdBy
     * @param updatedBy
     * @param divisionId
     */
    public static void modifyCustomer(int id, String name, String address, String postalCode, String phone, String createdBy, String updatedBy, int divisionId) {
        int Customer_ID = id;
        String Customer_Name = name;
        String Address = address;
        String Postal_Code = postalCode;
        String Phone = phone;
        String Last_Updated_By = updatedBy;
        int Division_ID = divisionId;

        String sql = "UPDATE customers \n" +
        "SET Customer_ID = '" + Customer_ID + "', Customer_Name = '" + Customer_Name + "', Address = \n'" +
                Address + "', Postal_Code = '" + Postal_Code + "', Phone = '" + Phone + "', Last_Update = NOW(),\n" +
                "Last_Updated_By = '" +Last_Updated_By + "', Division_ID = '" + Division_ID + "\n'" +
                "WHERE Customer_ID = '" + Customer_ID + "'";
        Query.makeQuery(sql);
    }
}
