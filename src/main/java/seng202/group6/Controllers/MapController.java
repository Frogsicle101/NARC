package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class MapController extends MasterController {

    @FXML
    private Button homeButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button importButton;

    public void clickHome(ActionEvent event) throws IOException {
        changeToHomeScreen(event);
    }

    public void clickData(ActionEvent event) throws IOException {
        changeToDataScreen(event);
    }

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen(event);
    }

}