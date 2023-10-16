package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Project: C195-Scheduler
 * package main;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This is the main method.  This is the first method that gets called when you run your java program.
 */
public class Main extends Application {

    @Override
    /**
     * Changes scene to LogIn and setup primary stage
     */
    public void start(Stage primaryStage) throws Exception{
        Locale currentLocale = Locale.getDefault();
        ResourceBundle resources = ResourceBundle.getBundle("main.Language", currentLocale);
        Locale locale = new Locale("fr");
        //ResourceBundle resources = ResourceBundle.getBundle("main.Language", locale);
        Parent root = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"), resources);
        primaryStage.setTitle("LogIn");
        primaryStage.setScene(new Scene(root, 300, 450));
        primaryStage.show();
    }

    /**
     * launches the database connection
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();

    }
}

