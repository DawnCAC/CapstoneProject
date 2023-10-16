package controller;

import dbaccess.DBAppointment;
import dbaccess.DBContact;
import dbaccess.DBUser;
import helper.DateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the AddModAppt.fxml
 */
public class AddModAppt implements Initializable {
    public TextField apptId;
    public TextField title;
    public TextField description;
    public TextField location;
    public ComboBox contact;
    public TextField type;
    public TextField customerId;
    public ComboBox startTime;
    public ComboBox endTime;
    public DatePicker startDate;
    public DatePicker endDate;
    public TextField UserId;
    public static String currentUser = null;
    public static Appointment currentAppt = null;
    public static int contactId = 0;
    public static int userId = 0;



    public static void currentUser(String CurrentUser) {currentUser = CurrentUser; }

    @Override
    /**
     * Initializes AddModAppt
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList startTimes = FXCollections.observableArrayList();
        ObservableList endTimes = FXCollections.observableArrayList();
        ObservableList contacts = FXCollections.observableArrayList();
        try {
            contacts.addAll(DBContact.getAllContacts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        contact.setItems(contacts);

        try {
            startTimes.addAll(DateTime.getApptTimes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        startTime.setItems(startTimes);

        try {
            endTimes.addAll(DateTime.getApptTimes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        endTime.setItems(endTimes);

        if (currentAppt != null){
            apptId.setText(String.valueOf(currentAppt.getApptID()));
            title.setText(currentAppt.getTitle());
            description.setText(currentAppt.getDescription());
            location.setText(currentAppt.getLocation());
            contact.setValue(currentAppt.getContact());
            type.setText(currentAppt.getType());
            startDate.setValue(currentAppt.getStart().toLocalDate());
            startTime.setValue(currentAppt.getStart().toLocalTime());
            endDate.setValue(currentAppt.getEnd().toLocalDate());
            endTime.setValue(currentAppt.getEnd().toLocalTime());
            customerId.setText(String.valueOf(currentAppt.getCustomerID()));
        }

        try {
            UserId.setText(String.valueOf(DBUser.getUserID(currentUser)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    /**
     * Retreives the current Appointment
     * @param ca
     */
    public static void apptToModify (Appointment ca) {currentAppt = ca;}

    /**
     * Saves appointments to datadase
     * @param mouseEvent
     * @throws IOException
     * @throws SQLException
     */
    public void saveToAppointments(MouseEvent mouseEvent) throws IOException, SQLException {
        LocalDateTime start = LocalDateTime.of(startDate.getValue(), (LocalTime) startTime.getValue());
        LocalDateTime end = LocalDateTime.of(endDate.getValue(), (LocalTime) endTime.getValue());
        int customer = Integer.parseInt(customerId.getText());
        String ApptID = apptId.getText();
        Boolean OutsideHours = DateTime.outsideHours(start, end);
        int count = DBAppointment.overlapAppt(customer, ApptID, start, end);
        System.out.println(start);
        if (count != 0){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Overlapping Appointments");
            errorAlert.setContentText("Please select a time that does not overlap with other appointments.");
            errorAlert.showAndWait();
            return;
        }

        if (OutsideHours == true){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Outside Business hours");
            errorAlert.setContentText("Please select a time within Business hours.");
            errorAlert.showAndWait();
            return;
        }

        if (currentAppt == null) {
            DBAppointment.newAppt(title.getText(), description.getText(), location.getText(), (String) contact.getValue(), type.getText(), start, end, Integer.parseInt(customerId.getText()), Integer.parseInt(UserId.getText()));
        }else {
            DBAppointment.modifyAppt(Integer.parseInt(apptId.getText()), title.getText(), description.getText(), location.getText(), (String) contact.getValue(), type.getText(), start, end, Integer.parseInt(customerId.getText()), Integer.parseInt(UserId.getText()));
        }
        currentAppt = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("Schedule");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes scene to Schedule
     * @param mouseEvent
     * @throws IOException
     */
    public void toAppointments(MouseEvent mouseEvent) throws IOException {
        currentAppt = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("Schedule");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Populates contacts to combo box
     * @param mouseEvent
     * @throws SQLException
     */
    public void populateContacts(MouseEvent mouseEvent) throws SQLException {
        ObservableList contacts = FXCollections.observableArrayList();
        contacts.addAll(DBContact.getAllContacts());
        contact.setItems(contacts);
    }

    /**
     * Populates start times to combo box
     * @param mouseEvent
     */
    public void populateStartTimes(MouseEvent mouseEvent) {
        ObservableList startTimes = FXCollections.observableArrayList();
        startTimes.addAll(DateTime.getApptTimes());
        startTime.setItems(startTimes);
    }

    /**
     * Populates end times to combo box
     * @param mouseEvent
     */
    public void populateEndTimes(MouseEvent mouseEvent) {
        ObservableList endTimes = FXCollections.observableArrayList();
        endTimes.addAll(DateTime.getApptTimes());
        endTime.setItems(endTimes);
    }

}
