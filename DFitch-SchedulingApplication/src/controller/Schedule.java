package controller;

import dbaccess.DBAppointment;
import dbaccess.DBCustomer;
import helper.DateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the Schedule.fxml
 */
public class Schedule implements Initializable {
    public TableView scheduleTable;
    public TableColumn apptId;
    public TableColumn apptTitle;
    public TableColumn apptDescription;
    public TableColumn apptLocation;
    public TableColumn apptContact;
    public TableColumn apptType;
    public TableColumn apptStart;
    public TableColumn apptEnd;
    public TableColumn apptCustomerId;
    public TableColumn apptUserId;
    public ToggleGroup WeekMonthSchedule;
    public RadioButton weekly;
    public RadioButton monthly;
    LocalDate start = null;
    LocalDate end = null;

    ObservableList<Appointment> apptSet = FXCollections.observableArrayList();

    public ObservableList<Appointment> getApptSet() { return apptSet; }

    @Override
    /**
     * Initializing the schedule.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptId.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        apptDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        apptLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        apptCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        apptUserId.setCellValueFactory(new PropertyValueFactory<>("UserID"));

        try {
            apptSet.addAll(DBAppointment.getAllAppts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        scheduleTable.getItems().clear();
        scheduleTable.setItems(apptSet);

        ObservableList<Appointment> foundAppointments = null;
        try {
            foundAppointments = sortByRadioButtons();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        scheduleTable.getItems().clear();
        scheduleTable.setItems(foundAppointments);

    }

    /**
     * Changes scene to AddModAppt
     * @param mouseEvent
     * @throws IOException
     */
    public void toAddSchedule(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddModAppt.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 500, 700);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes scene to AddModAppt
     * @param mouseEvent
     * @throws IOException
     */
    public void toModifySchedule(MouseEvent mouseEvent) throws IOException {
        Appointment appt = (Appointment) scheduleTable.getSelectionModel().getSelectedItem();
        AddModAppt.apptToModify(appt);
        if (appt == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR) ;
            errorAlert.setTitle("Nothing Selected");
            errorAlert.setContentText("Please select an Appointment");
            errorAlert.showAndWait();
            return;
        }else {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AddModAppt.fxml"));
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 500, 700);
            stage.setTitle("Modify Appointment");
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Deletes appointments.
     * @param mouseEvent
     */
    public void deleteSchedule(MouseEvent mouseEvent) {
        Appointment appt = (Appointment) scheduleTable.getSelectionModel().getSelectedItem();
        if (appt == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR) ;
            errorAlert.setTitle("Nothing Selected");
            errorAlert.setContentText("Please select an Appointment");
            errorAlert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you wish to delete Appointment?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int currentId = appt.getApptID();
            String type = appt.getType();
            try {
                DBAppointment.deleteAppt(currentId, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            scheduleTable.getItems().clear();
            try {
                apptSet.addAll(DBAppointment.getAllAppts());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            scheduleTable.setItems(apptSet);
        }

    }

    /**
     * Changes scene to MainMenu
     * @param mouseEvent
     * @throws IOException
     */
    public void toMainMenu(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 300, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sorts appointments based on which radio button is selected
     * @return
     * @throws SQLException
     */
    private ObservableList<Appointment> sortByRadioButtons() throws SQLException {
        ObservableList<Appointment> foundAppointments = FXCollections.observableArrayList();
        if (weekly.isSelected()){
            start = DateTime.weekStartDate();
            end = start.plusDays(7);
            foundAppointments.addAll(DBAppointment.getSortAppts(start, end));
        }else {
            start = DateTime.monthStartDate();
            end = start.plusMonths(1);
            foundAppointments.addAll(DBAppointment.getSortAppts(start, end));
        }
        return foundAppointments;
    }

    /**
     * Sets selected appointments to the table based on Weekly sorting
     * @param actionEvent
     * @throws SQLException
     */
    public void onWeekly(ActionEvent actionEvent) throws SQLException {
        scheduleTable.getItems().clear();
        ObservableList<Appointment> foundAppointments = sortByRadioButtons();
        scheduleTable.setItems(foundAppointments);
    }

    /**
     * Sets selected appointments to the table based on Monthly sorting
     * @param actionEvent
     * @throws SQLException
     */
    public void onMonthly(ActionEvent actionEvent) throws SQLException {
        scheduleTable.getItems().clear();
        ObservableList<Appointment> foundAppointments = sortByRadioButtons();
        scheduleTable.setItems(foundAppointments);
    }

    /**
     * Sorts appointments based on previous week or month
     * @param actionEvent
     * @throws SQLException
     */
    public void toPrevAppts(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment> foundAppointments = FXCollections.observableArrayList();
        if (weekly.isSelected()){
            start = start.minusWeeks(1);
            LocalDate end = start.plusWeeks(1);
            foundAppointments.addAll(DBAppointment.getSortAppts(start, end));
        }else{
            start = start.minusMonths(1);
            end = start.plusMonths(1);
            foundAppointments.addAll(DBAppointment.getSortAppts(start, end));
        }
        scheduleTable.getItems().clear();
        scheduleTable.setItems(foundAppointments);
    }

    /**
     * Sorts appointments based on the next week or month
     * @param actionEvent
     * @throws SQLException
     */
    public void toNextAppts(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment> foundAppointments = FXCollections.observableArrayList();
        if (weekly.isSelected()){
            start = start.plusWeeks(1);
            end = start.plusWeeks(1);
            foundAppointments.addAll(DBAppointment.getSortAppts(start, end));
        }else{
            start = start.plusMonths(1);
            end = start.plusMonths(1);
            foundAppointments.addAll(DBAppointment.getSortAppts(start, end));
        }
        scheduleTable.getItems().clear();
        scheduleTable.setItems(foundAppointments);
    }
}
