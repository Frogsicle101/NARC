package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Master Controller class used for switching between scenes and creating
 * new windows when needed
 */

public class MasterController {

    protected static ArrayList<Crime> crimeData;
    protected static Crime crimeToView;

    private Parent homeScreen;
    private Parent mapScreen;
    private Parent dataScreen;
    private Parent importScreen;

    /**
     * Method to change between scenes in the current window
     * @param screen A string telling the method with screen to switch to
     * @param event Button clicked event which allows method to find current stage
     * @throws IOException
     */

    public void changeScreen(String screen, ActionEvent event) throws IOException {

        Parent newScreen = FXMLLoader.load(getClass().getResource(screen));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newScreen, 1050, 640);
        currentStage.setScene(newScene);
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the home screen
     * @param event Button clicked event which allows method to find current stage
     * @throws IOException
     */

    public void changeToHomeScreen(ActionEvent event) throws IOException {
        changeScreen("homeScreen.fxml", event);
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the map screen
     * @param event Button clicked event which allows method to find current stage
     * @throws IOException
     */

    public void changeToMapScreen(ActionEvent event) throws IOException {
        changeScreen("mapScreen.fxml", event);
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the data screen
     * @param event Button clicked event which allows method to find current stage
     * @throws IOException
     */

    public void changeToDataScreen(ActionEvent event) throws IOException {
        changeScreen("dataScreen.fxml", event);
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the map screen
     * @param event Button clicked event which allows method to find current stage
     * @throws IOException
     */

    public void changeToImportScreen(ActionEvent event) throws IOException {
        changeScreen("importScreen.fxml", event);
    }

    /**
     * Method to launch new window for viewing specific crime in order to show detailed
     * attributes, takes a specific crime as a parameter
     * @param event Button clicked event which allows method to find current stage
     * @param crime Specific crime selected by user to view detailed description
     * @throws IOException
     */

    public void launchViewScreen(ActionEvent event, Crime crime) throws IOException {

        crimeToView = crime;
        Stage viewStage = new Stage();
        viewStage.setTitle("View Crime Info");
        Parent newScreen = FXMLLoader.load(getClass().getResource("viewCrimeScreen.fxml"));
        Scene newScene = new Scene(newScreen, 700, 500);
        viewStage.setScene(newScene);
        viewStage.show();

    }


}
