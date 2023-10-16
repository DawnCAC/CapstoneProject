package controller;

import dbaccess.DBAppointment;
import dbaccess.DBContact;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the ContactSchedule.fxml
 */
public class ContactSchedule implements Initializable {
    public TableView scheduleTable;
    public TableColumn apptId;
    public TableColumn apptTitle;
    public TableColumn apptCustomerId;
    public TableColumn apptType;
    public TableColumn apptDescription;
    public TableColumn apptStart;
    public TableColumn apptEnd;
    public ComboBox currentContact;

    ObservableList<Appointment> apptSet = FXCollections.observableArrayList();
    ObservableList contacts = FXCollections.observableArrayList();

    /**
     * Initializes the contact schedule
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        apptId.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        apptDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        apptCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        try {
            apptSet.addAll(DBAppointment.getAllAppts());
            scheduleTable.setItems(apptSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            contacts.addAll(DBContact.getAllContacts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        currentContact.setItems(contacts);

    }

    /**
     * Filters appointments based on the contact selected
     * Lambda expression is used to compare contacts for filtering
     * @param actionEvent
     */
    public void onSelectContact(ActionEvent actionEvent) {
        //ObservableList<Appointment> foundAppt = FXCollections.observableArrayList();
        String contact = currentContact.getValue().toString();
        System.out.println(contact);  //shows that the contact was successfully pulled from the combobox
        ObservableList<Appointment> foundAppt = apptSet.stream()
                .filter(appt -> contact.equals(appt.getContact()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        // the above is not sorting the appointments, it is just passing all of them back
        //when it filtered with the non-lambda it would only filter part of the time
        System.out.println(foundAppt); //show that there is appointments in this observable list
        scheduleTable.setItems(foundAppt);
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


}
