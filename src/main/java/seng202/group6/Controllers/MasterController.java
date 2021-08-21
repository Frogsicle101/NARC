package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MasterController {

    public void changeScreen(String screen, ActionEvent event) throws IOException {
        Parent newScreen = FXMLLoader.load(getClass().getResource(screen));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newScreen, 1050, 640);
        currentStage.setScene(newScene);
    }

    public void changeToHomeScreen(ActionEvent event) throws IOException {
        changeScreen("homeScreen.fxml", event);
    }

    public void changeToMapScreen(ActionEvent event) throws IOException {
        changeScreen("mapScreen.fxml", event);
    }

    public void changeToDataScreen(ActionEvent event) throws IOException {
        changeScreen("dataScreen.fxml", event);
    }

    public void changeToImportScreen(ActionEvent event) throws IOException {
        changeScreen("importScreen.fxml", event);
    }




}
