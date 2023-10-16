package controller;

import dbaccess.DBCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the Customers.fxml
 */
public class Customers implements Initializable{
    public TableView customerTable;
    public TableColumn customerID;
    public TableColumn customerName;
    public TableColumn customerAddress;
    public TableColumn customerDivision;
    public TableColumn customerCountry;
    public TableColumn customerPostalCode;
    public TableColumn customerPhone;

    ObservableList<Customer> customerSet = FXCollections.observableArrayList();

    @Override
    /**
     * Initializes the Customers class
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        customerDivision.setCellValueFactory(new PropertyValueFactory<>("Division"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<>("Country"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));

        //Getting customers and setting them to the table
        try {
           customerSet.addAll(DBCustomer.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        customerTable.getItems().clear();
        customerTable.setItems(customerSet);
    }

    /**
     * Changes scene to AddModCustomer
     * @param mouseEvent
     * @throws IOException
     */
    public void toAddCustomer(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddModCustomer.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 500, 700);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Passes selected customer to modifyCustomer screen
     * @param mouseEvent
     * @throws IOException
     */
    public void toModifyCustomer(MouseEvent mouseEvent) throws IOException {
        Customer cc = (Customer) customerTable.getSelectionModel().getSelectedItem();
        AddModCustomer.customerToModify(cc);
        if (cc == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR) ;
            errorAlert.setTitle("Nothing Selected");
            errorAlert.setContentText("Please select a Customer");
            errorAlert.showAndWait();
            return;
        }else {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AddModCustomer.fxml"));
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 500, 700);
            stage.setTitle("Modify Customer");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Deletes customer
     * @param mouseEvent
     * @throws IOException
     */
    public void deleteCustomer(MouseEvent mouseEvent) throws IOException {
        Customer cc = (Customer) customerTable.getSelectionModel().getSelectedItem();
        if (cc == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR) ;
            errorAlert.setTitle("Nothing Selected");
            errorAlert.setContentText("Please select a Customer");
            errorAlert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you wish to delete Customer?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int currentId = cc.getId();
            try {
                DBCustomer.deleteCustomer(currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            customerTable.getItems().clear();
            try {
                customerSet.addAll(DBCustomer.getAllCustomers());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            customerTable.setItems(customerSet);
        }

    }

    /**
     * Changes scene to MainMenu
     * @param mouseEvent
     * @throws IOException
     */
    public void toMain(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 300, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
