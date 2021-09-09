package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import seng202.group6.Services.SQLiteDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for home screen in user interface, associated with homeScreen.fxml.
 * Is a child class of MasterController
 */

public class HomeController extends MasterController implements Initializable {

    @FXML
    protected static Button homeButton;

    @FXML
    protected static Button mapButton;

    @FXML
    protected static Button dataButton;

    @FXML
    protected static Button importButton;

    @FXML
    protected static Text welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Method to call change to home screen method in MasterController when the home button
     * is clicked
     * @throws IOException
     */

    public void clickMap() throws IOException {
        changeToMapScreen();
    }

    /**
     * Method to call change to data screen method in MasterController when the data button
     * is clicked
     * @throws IOException
     */

    public void clickData() throws IOException {
        changeToDataScreen();
    }

    public void clickImport() throws IOException {
        changeToImportScreen();
    }

}