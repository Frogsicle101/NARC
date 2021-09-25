package seng202.group6.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;
import seng202.group6.Services.Filter;
import seng202.group6.Services.SQLiteDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Master Controller class used for switching between scenes and creating
 * new windows when needed
 */

public class MasterController {


    protected static ArrayList<Crime> crimeData = new ArrayList<>();
    protected static Crime currentCrime;
    protected static Stage stage;
    protected static Filter dataFilter;
    protected static boolean choseMap = false;


    public static void populateCrimeArray(String tableName) {
        //Populates crimeData arraylist from database
        try {
            crimeData = SQLiteDatabase.convertResultSet(SQLiteDatabase.selectAllFromTable(tableName));
        } catch (SQLException e) {
            System.out.println("Error in MasterController.populateCrimeArray" + e);
        }
    }
    /**
     * Method to change between scenes in the current window
     * @param screen A string telling the method with screen to switch to
     * @throws IOException ioexception
     */
    public void changeScreen(String screen) throws IOException {

        Parent newScreen = FXMLLoader.load(getClass().getResource(screen));
        Scene newScene = new Scene(newScreen, 1200, 700);
        stage.setScene(newScene);
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the home screen
     * @throws IOException ioexception
     */

    public void changeToHomeScreen() throws IOException {
        changeScreen("homeScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the map screen
     * @throws IOException ioexception
     */

    public void changeToMapScreen() throws IOException {
        choseMap = true;
        changeScreen("dataScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the data screen
     * @throws IOException ioexception
     */

    public void changeToDataScreen() throws IOException {
        choseMap = false;
        changeScreen("dataScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the map screen
     * @throws IOException ioexception
     */

    public void changeToImportScreen() throws IOException {
        changeScreen("importScreen.fxml");
    }

    /**
     * Method to call change screen method with parameter screen to
     * change current scene to the graph screen
     * @throws IOException ioexception
     */

    public void changeToGraphScreen() throws IOException {
        changeScreen("graphScreen.fxml");
    }

    /**
     * Method to launch new window for viewing specific crime in order to show detailed
     * attributes, takes a specific crime as a parameter
     * @param crime Specific crime selected by user to view detailed description
     */

    public static void launchViewScreen(Crime crime) throws IOException {

        currentCrime = crime;
        Stage viewStage = new Stage();
        viewStage.setTitle("View Crime Info");
        Parent newScreen = FXMLLoader.load(MasterController.class.getResource("viewCrimeScreen.fxml"));
        Scene newScene = new Scene(newScreen, 700, 550);
        viewStage.setScene(newScene);
        viewStage.show();

    }

    public void launchCrimeRankScreen() throws IOException {


        Stage viewStage = new Stage();
        viewStage.setTitle("Crime Ranking");
        Parent newScreen = FXMLLoader.load(getClass().getResource("viewCrimeRankingScreen.fxml"));
        Scene newScene = new Scene(newScreen, 700, 500);
        viewStage.setScene(newScene);
        viewStage.show();
    }

    public void launchAreaRankScreen() throws IOException {

        Stage viewStage = new Stage();
        viewStage.setTitle("Area Ranking");
        Parent newScreen = FXMLLoader.load(getClass().getResource("viewAreaRankingScreen.fxml"));
        Scene newScene = new Scene(newScreen, 700, 500);
        viewStage.setScene(newScene);
        viewStage.show();
    }

    public void launchTimeRankScreen() throws IOException {

        Stage viewStage = new Stage();
        viewStage.setTitle("Hour of the Day Ranking");
        Parent newScreen = FXMLLoader.load(getClass().getResource("viewTimeRankingScreen.fxml")); //Causing problems
        Scene newScene = new Scene(newScreen, 700, 500);
        viewStage.setScene(newScene);
        viewStage.show();
    }




    public void launchEditScreen(Crime crime) throws IOException {

        currentCrime = crime;
        Stage viewStage = new Stage();
        viewStage.setTitle("Edit Crime Info");
        Parent newScreen = FXMLLoader.load(getClass().getResource("editCrimeScreen.fxml"));
        Scene newScene = new Scene(newScreen, 500, 600);
        viewStage.setScene(newScene);
        viewStage.show();

    }

    public static Filter getFilter() {
        return dataFilter;
    }


}
