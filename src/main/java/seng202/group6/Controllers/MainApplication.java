package seng202.group6.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;

import java.io.IOException;

/**
 * Main application class for the user interface, launches an initial stage, displaying
 * the home screen of the user interface
 */

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        MasterController.stage = primaryStage;
        Parent homeScreen = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        primaryStage.setTitle("NARC");
        primaryStage.setScene(new Scene(homeScreen, 1050, 640));
        primaryStage.show();
    }

    /**
     * Calls launch method from Application class
     * @param args String[] value of given arguments to main method
     */

    public static void main(String[] args) {
        launch();
    }

}