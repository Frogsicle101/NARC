package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

/**
 * Controller class for home screen in user interface, associated with homeScreen.fxml.
 * Is a child class of MasterController
 */

public class HomeController extends MasterController {

    @FXML
    private Button homeButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button importButton;

    /**
     * Method to call change to home screen method in MasterController when the home button
     * is clicked
     * @param event Button click event when home button is clicked
     * @throws IOException
     */

    public void clickMap(ActionEvent event) throws IOException {
        changeToMapScreen(event);
    }

    /**
     * Method to call change to data screen method in MasterController when the data button
     * is clicked
     * @param event Button click event when data button is clicked
     * @throws IOException
     */

    public void clickData(ActionEvent event) throws IOException {
        changeToDataScreen(event);
    }

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen(event);
    }

}