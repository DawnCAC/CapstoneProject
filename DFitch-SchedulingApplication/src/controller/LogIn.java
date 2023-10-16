package controller;

import dbaccess.DBUser;
import interfaces.LogInTracker;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Project: C195-Scheduler
 * Package: package controller;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class is the controller class for the LogIn.fxml
 */
public class LogIn implements Initializable {
    public Label zonedLocation;
    public TextField userName;
    public TextField password;
    public ResourceBundle Language;
    /**
     * The Lambda Expression formats the entry for the login_activity.txt.
     */
    public LogInTracker activity = (currentUser, ts, message) -> {
        String str1 = currentUser + ", " + ts.toString() + " UTC" + message;
        return str1;
    };

    @Override
    /**
     * Initializes Login
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Language = resourceBundle;
        zonedLocation.setText(Locale.getDefault().getDisplayCountry());
        //zonedLocation.setText("France");
    }

    /**
     * Changes scene to MainMenu.
     * @param mouseEvent
     * @throws IOException
     * @throws SQLException
     */
    public void logInToMainMenu(MouseEvent mouseEvent) throws IOException, SQLException {
        if (userName.getText().length() == 0 || password.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Language.getString("Error"));
            alert.setContentText(Language.getString("Error_Blank"));
            alert.showAndWait();
            return;
        }else if (DBUser.authenticateUser(userName.getText(), password.getText()) == true) {
            String currentUser = userName.getText();
            //LocalDateTime ldt = LocalDateTime.now();
            //LocalDate ld = ldt.toLocalDate();
            //ZonedDateTime utc = ldt.atZone(ZoneId.of("UTC"));
            ZonedDateTime utc = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
            Timestamp ts = Timestamp.valueOf(utc.toLocalDateTime());
            String filename = "login_activity.txt", item;
            FileWriter fw = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fw);
            outputFile.println(activity.string(currentUser, ts, ", Success"));
            outputFile.close();
            AddModCustomer.currentUser(currentUser);
            AddModAppt.currentUser(currentUser);
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 300, 450);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }else {
            String currentUser = userName.getText();
            //LocalDateTime ldt = LocalDateTime.now();
            //LocalDate ld = ldt.toLocalDate();
            //ZonedDateTime utc = ldt.atZone(ZoneId.of("UTC"));
            ZonedDateTime utc = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
            Timestamp ts = Timestamp.valueOf(utc.toLocalDateTime());
            String filename = "login_activity.txt", item;
            FileWriter fw = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fw);
            outputFile.println(activity.string(currentUser, ts, ", Fail"));
            outputFile.close();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Language.getString("Error"));
            alert.setContentText(Language.getString("Invalid_Credentials"));
            alert.showAndWait();
            return;
        }
    }

    /**
     * Cancels login which exits program
     * @param mouseEvent
     * @throws IOException
     */
    public void exit(MouseEvent mouseEvent) throws IOException {System.exit(0); }
}
