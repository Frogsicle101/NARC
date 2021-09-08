package seng202.group6.Controllers;

import com.opencsv.exceptions.CsvValidationException;
import seng202.group6.Controllers.DataController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;
import seng202.group6.Services.ParserService;
import seng202.group6.Services.SQLiteDatabase;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller class for import screen in user interface, is associated with importScreen.fxml.
 * Is a child class of MasterController
 */

public class ImportController extends MasterController {

    @FXML
    private Button homeButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button importButton;

    @FXML
    private Button importFileButton;

    @FXML
    private Text uploadSuccess;

    /**
     * Method to call change to home screen method in MasterController when the home button
     * is clicked
     * @throws IOException
     */
    
    public void clickHome() throws IOException {
        changeToHomeScreen();
    }

    /**
     * Method to call change to map screen method in MasterController when the map button
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

    /**
     * Method to import a file when import file button is clicked. Creates a new file
     * chooser instance which opens on the users computer to select a file from the
     * local system. Checks if the file uploaded is valid and if it is will send the
     * data to ParserService to read. If file is not valid will show an error message
     * on screen
     * @param event Button click event when import file button is clicked
     * @throws IOException
     * @throws CsvValidationException
     */

    public void clickImportFile(ActionEvent event) throws IOException, CsvValidationException, SQLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open crime data file");

        boolean validUpload;
        File crimeFile = fileChooser.showOpenDialog(stage);
        if (crimeFile == null) {
            validUpload = false;
        } else {
            SQLiteDatabase.createTable("Crimes");
            crimeData.addAll(ParserService.csvToArrayList(crimeFile)); //TODO: deal with thrown exceptions



            filteredCrimeData = crimeData;
            // need to make method to check if file is csv format and if they actually selected a file
            // also need to get checks for correct format in parser
            validUpload = true;
        }

        if (validUpload) {
            uploadSuccess.setVisible(true);
        }


    }
}
