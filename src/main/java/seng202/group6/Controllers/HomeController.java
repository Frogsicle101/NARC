package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.io.IOException;

public class HomeController extends MasterController {

    @FXML
    private Button homeButton;
    //homeButton.setStyle("-fx-background-color: MediumSeaGreen");

    @FXML
    private Button mapButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button importButton;

    public void clickMap(ActionEvent event) throws IOException {
        changeToMapScreen(event);
    }

    public void clickData(ActionEvent event) throws IOException {
        changeToDataScreen(event);
    }

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen(event);
    }

}