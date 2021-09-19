package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;
import seng202.group6.Models.CrimeFrequency;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Master Controller class used for switching between scenes and creating
 * new windows when needed
 */

public class MasterController {


    protected static ArrayList<Crime> crimeData = new ArrayList<>();
    protected static ArrayList<Crime> filteredCrimeData = new ArrayList<>();
    protected static Crime crimeToView;
    protected static Stage stage;


    /**
     * Method to change between scenes in the current window
     * @param screen A string telling the method with screen to switch to
     * @throws IOException
     */

    public void changeScreen(String screen) throws IOException {

        Parent newScreen = FXMLLoader.load(getClass().getResource(screen));
        Scene newScene = new Scene(newScreen, 1200, 700);
        stage.setScene(newScene);
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the home screen
     * @throws IOException
     */

    public void changeToHomeScreen() throws IOException {
        changeScreen("homeScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the map screen
     * @throws IOException
     */

    public void changeToMapScreen() throws IOException {
        changeScreen("mapScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the data screen
     * @throws IOException
     */

    public void changeToDataScreen() throws IOException {
        changeScreen("dataScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the map screen
     * @throws IOException
     */

    public void changeToImportScreen() throws IOException {
        changeScreen("importScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the graph screen
     * @throws IOException
     */

    public void changeToGraphScreen() throws IOException {
        changeScreen("graphScreen.fxml");
    }

    /**
     * Method to launch new window for viewing specific crime in order to show detailed
     * attributes, takes a specific crime as a parameter
     * @param crime Specific crime selected by user to view detailed description
     */

    public void launchViewScreen(Crime crime) throws IOException {

        crimeToView = crime;
        Stage viewStage = new Stage();
        viewStage.setTitle("View Crime Info");
        Parent newScreen = FXMLLoader.load(getClass().getResource("viewCrimeScreen.fxml"));
        Scene newScene = new Scene(newScreen, 700, 550);
        viewStage.setScene(newScene);
        viewStage.show();

    }

    public void launchRankScreen() throws IOException {


        Stage viewStage = new Stage();
        viewStage.setTitle("Crime Ranking");
        Parent newScreen = FXMLLoader.load(getClass().getResource("viewCrimeRankingScreen.fxml"));
        Scene newScene = new Scene(newScreen, 700, 500);
        viewStage.setScene(newScene);
        viewStage.show();
    }

    public void launchRankScreen2() throws IOException {

        Stage viewStage = new Stage();
        viewStage.setTitle("Area Ranking");
        Parent newScreen = FXMLLoader.load(getClass().getResource("viewAreaRankingScreen.fxml")); //Causing problems
        Scene newScene = new Scene(newScreen, 700, 500);
        viewStage.setScene(newScene);
        viewStage.show();
    }


    public void launchEditScreen(Crime crime) throws IOException {

        crimeToView = crime;
        Stage viewStage = new Stage();
        viewStage.setTitle("Edit Crime Info");
        Parent newScreen = FXMLLoader.load(getClass().getResource("editCrimeScreen.fxml"));
        Scene newScene = new Scene(newScreen, 500, 550);
        viewStage.setScene(newScene);
        viewStage.show();

    }


}
