package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import seng202.group6.Services.MapService;
import javafx.scene.web.WebEngine;

import java.awt.image.BufferedImage;
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

    @FXML
    private ImageView mapImage;

    @FXML
    private Button viewMapButton;

    @FXML
    private TextField addressField;

    public void clickHome(ActionEvent event) throws IOException {
        changeToHomeScreen(event);
    }

    public void clickData(ActionEvent event) throws IOException {
        changeToDataScreen(event);
    }

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen(event);
    }

    public void clickViewMap(ActionEvent event) throws IOException {
        String address = addressField.getText().toString();
        address = address.replace(' ', '+');
        String testImage = "https://maps.googleapis.com/maps/api/staticmap?" +
                "center=" + address +
                "&zoom=10" +
                "&size=600x300" +
                "&maptype=roadmap" +
                "&key=AIzaSyBZgxE6A5nvnM7aYqg49wDdK_SPKXqdLiE";
        Image image = MapService.getImage(testImage);
        mapImage.setImage(image);

    }

}