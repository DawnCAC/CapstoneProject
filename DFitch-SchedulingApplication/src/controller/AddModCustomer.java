package controller;

import dbaccess.DBCountry;
import dbaccess.DBCustomer;
import dbaccess.DBFirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the AddModCustomer.fxml
 */
public class AddModCustomer implements Initializable {
    public TextField customerId;
    public TextField customerName;
    public TextField address;
    public TextField postalCode;
    public TextField phone;
    public ComboBox division;
    public ComboBox country;
    public String selectedCountry;
    public String Division;
    public static int division_ID = 0;
    public static String CurrentUser = null;
    public static Customer currentCustomer = null;

    public static void currentUser(String currentUser) { CurrentUser = currentUser; }


    @Override
    /**
     * Initializes AddModCustomer
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList currentDivisions = FXCollections.observableArrayList();
        ObservableList currentCountry = FXCollections.observableArrayList();

        try {
            currentDivisions.addAll(DBFirstLevelDivision.getAllRecords());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        division.setItems(currentDivisions);

        try {
            currentCountry.addAll(DBCountry.getAllRecords());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        country.setItems(currentCountry);

        if (currentCustomer != null){
            customerId.setText(String.valueOf(currentCustomer.getId()));
            customerName.setText(currentCustomer.getName());
            address.setText(currentCustomer.getAddress());
            postalCode.setText(currentCustomer.getPostalCode());
            phone.setText(currentCustomer.getPhone());
            division.setValue(currentCustomer.getDivision());
            country.setValue(currentCustomer.getCountry());
        }
    }

    /**
     * Retreives current customer
     * @param cc
     */
    public static void customerToModify (Customer cc) {currentCustomer = cc;}

    /**
     * Gets Division Id
     * @return
     */
    public int getDivisionId(){
        Division = (String) division.getValue();
        try{
            division_ID = DBFirstLevelDivision.getDivisionID(Division);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return division_ID;
    }

    /**
     * Saves new or modified customers
     * @param mouseEvent
     * @throws IOException
     * @throws SQLException
     */
    public void saveToCustomers(MouseEvent mouseEvent) throws IOException, SQLException {
        if (currentCustomer == null){
            division_ID = getDivisionId();
            DBCustomer.newCustomer(customerName.getText(), address.getText(), postalCode.getText(), phone.getText(),
                    CurrentUser, CurrentUser, division_ID);

        }else {
            division_ID = getDivisionId();
            DBCustomer.modifyCustomer(Integer.parseInt(customerId.getText()), customerName.getText(), address.getText(), postalCode.getText(), phone.getText(),
                    CurrentUser, CurrentUser, division_ID);
        }
        currentCustomer = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes scene to Customers
     * @param mouseEvent
     * @throws IOException
     */
    public void toCustomers(MouseEvent mouseEvent) throws IOException {
        currentCustomer = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Populates the Division combobox
     * @param mouseEvent
     * @throws SQLException
     */
    public void populateDivision(MouseEvent mouseEvent) throws SQLException {
        ObservableList currentDivisions = FXCollections.observableArrayList();
        if (country.getValue() == null){
            try {
            currentDivisions.addAll(DBFirstLevelDivision.getAllRecords());
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
          division.setItems(currentDivisions);
        }else {
            selectedCountry = (String) country.getValue();
            try {
                currentDivisions.addAll(DBFirstLevelDivision.getDivisionFromCountry(selectedCountry));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            division.setItems(currentDivisions);
        }
    }

    /**
     * Populates the Country combobox
     * @param mouseEvent
     */
    public void populateCountry(MouseEvent mouseEvent) {
        ObservableList currentCountry = FXCollections.observableArrayList();
        try {
            currentCountry.addAll(DBCountry.getAllRecords());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        country.setItems(currentCountry);
    }
}
