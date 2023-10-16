package controller;

import dbaccess.DBAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the Reports.fxml
 */
public class Reports {
    /**
     * Changes scene to Appointment Totals report page
     * @param mouseEvent
     * @throws SQLException
     * @throws IOException
     */
    public void reportOne(MouseEvent mouseEvent) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ApptTotals.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 440, 800);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes scene to the Contact Schedule reports page
     * @param mouseEvent
     * @throws IOException
     */
    public void reportTwo(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ContactSchedule.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 942, 800);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes scene to the Appointments by Country reports page
     * @param mouseEvent
     * @throws IOException
     */
    public void reportThree(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ApptByCountry.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 440, 800);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Takes user back to the main menu
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
}
