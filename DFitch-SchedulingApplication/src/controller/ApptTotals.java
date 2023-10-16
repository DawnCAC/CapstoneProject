package controller;

import dbaccess.DBAppointment;
import helper.DateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the ApptTotals.fxml
 */
public class ApptTotals implements Initializable {
    public TableView scheduleTable;
    public TableColumn typeMonth;
    public TableColumn totals;
    public RadioButton TotalType;
    public RadioButton TotalMonth;
    public ToggleGroup ByTypeOrMonth;


    /**
     * Initializes the Appointment Totals Report.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeMonth.setCellValueFactory(new PropertyValueFactory<>("Type"));
        totals.setCellValueFactory(new PropertyValueFactory<>("Count"));

        ObservableList<Appointment> foundAppointments = null;
        try {
            foundAppointments = sortByRadioButtons();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        scheduleTable.setItems(foundAppointments);

    }

    /**
     * Calls Queries based on which radio button is selected
      * @return
     * @throws SQLException
     */
    private ObservableList<Appointment> sortByRadioButtons() throws SQLException {
        ObservableList<Appointment> foundAppointments = FXCollections.observableArrayList();
        if (TotalType.isSelected()){
            typeMonth.setText("Type");
            foundAppointments.addAll(DBAppointment.getApptByType());
        }else {
            typeMonth.setText("Month");
            foundAppointments.addAll(DBAppointment.getApptByMonth());
        }
        return foundAppointments;
    }

    /**
     * Sets items to the table based on Type sorting
     * @param actionEvent
     * @throws SQLException
     */
    public void onType(ActionEvent actionEvent) throws SQLException {
        scheduleTable.getItems().clear();
        ObservableList<Appointment> foundAppointments = sortByRadioButtons();
        scheduleTable.setItems(foundAppointments);
    }

    /**
     * Sets items to the table based on Month sorting
     * @param actionEvent
     * @throws SQLException
     */
    public void onMonth(ActionEvent actionEvent) throws SQLException {
        scheduleTable.getItems().clear();
        ObservableList<Appointment> foundAppointments = sortByRadioButtons();
        scheduleTable.setItems(foundAppointments);
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
