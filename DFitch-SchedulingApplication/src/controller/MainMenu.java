package controller;

import dbaccess.DBAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Formattable;
import java.util.ResourceBundle;
/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the MainMenu.fxml
 */
public class MainMenu implements Initializable {

    public Label AlertAppt;
    ObservableList<Appointment> currentApptAlert = FXCollections.observableArrayList();
    @Override
    /**
     * Initializes main menu
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String appts = "";
        try {
            currentApptAlert = DBAppointment.getApptAlert(LocalDateTime.now());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (currentApptAlert.isEmpty()){
            AlertAppt.setText("There are no upcoming appointments");
        }else {
            for (int i = 0; i < currentApptAlert.size(); i++){
                appts = appts + "\n Appt Id: " + currentApptAlert.get(i).getApptID() + ", " + currentApptAlert.get(i).getStart();
            }
            AlertAppt.setText(String.valueOf(appts));
        }
    }

    /**
     * Changes scene to Schedule
     * @param mouseEvent
     * @throws IOException
     */
    public void toSchedule(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("Schedule");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes scene to Customers table
     * @param mouseEvent
     * @throws IOException
     */
    public void toCustomers(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
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
     * Changes scene to the reports menu
     * @param mouseEvent
     * @throws IOException
     */
    public void toReports(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 300, 450);
        stage.setTitle("Reporting");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exits program
     * @param mouseEvent
     */
    public void exitProgram(MouseEvent mouseEvent) {System.exit(0); }

}
