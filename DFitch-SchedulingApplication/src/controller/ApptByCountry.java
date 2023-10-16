package controller;

import dbaccess.DBAppointment;
import dbaccess.DBCountry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the ApptByCountry.fxml
 */
public class ApptByCountry implements Initializable {
    public TableView scheduleTable;
    public TableColumn country;
    public TableColumn totals;

    ObservableList<Country> totalSet = FXCollections.observableArrayList();
    /**
     * Initializes the Appointments By Country Report.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        country.setCellValueFactory(new PropertyValueFactory<>("Country"));
        totals.setCellValueFactory(new PropertyValueFactory<>("Total"));

        try {
            totalSet = DBCountry.getApptByCountry();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        scheduleTable.setItems(totalSet);
    }

    /**
     * Changes scene to reports
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
