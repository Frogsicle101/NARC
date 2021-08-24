package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;
import seng202.group6.ParserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

    public void clickHome(ActionEvent event) throws IOException {
        changeToHomeScreen(event);
    }

    public void clickMap(ActionEvent event) throws IOException {
        changeToMapScreen(event);
    }


    public void clickData(ActionEvent event) throws IOException {
        changeToDataScreen(event);
    }

    public void clickImportFile(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open crime data file");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        boolean validUpload;
        File crimeFile = fileChooser.showOpenDialog(currentStage);
        if (crimeFile == null) {
            validUpload = false;
        } else {
            ArrayList<Crime> crimeArray = ParserService.csvToArrayList(crimeFile);
            // need to make method to check if file is csv format and if they actually selected a file
            // also need to get checks for correct format in parser}
            validUpload = true;
        }

        if (validUpload) {
            uploadSuccess.setVisible(true);
        }


    }
}
