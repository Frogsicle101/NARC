package seng202.group6.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        primaryStage.setTitle("NARC");
        primaryStage.setScene(new Scene(homeScreen, 1050, 640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}