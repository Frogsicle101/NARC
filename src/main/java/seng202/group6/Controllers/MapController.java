package seng202.group6.Controllers;

import com.google.maps.ImageResult;
import com.google.maps.model.LatLng;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import seng202.group6.Services.MapService;
import javafx.scene.web.WebEngine;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static seng202.group6.Services.MapService.*;

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

        /*String testImage = "https://maps.googleapis.com/maps/api/staticmap?" +
                "center=" + coord +
                "&zoom=10" +
                "&size=600x300" +
                "&maptype=roadmap" +
                "&key=AIzaSyBZgxE6A5nvnM7aYqg49wDdK_SPKXqdLiE";
        Image image = getImage(testImage);
        mapImage.setImage(image);*/
/*
        LatLng coord = null;
        try {
            coord = geoCodeAddress();
            ImageResult imageResult = getImage(coord);
            System.out.println(imageResult);
            BufferedImage image = null;
            ImageIO.write(image, "png", imageResult.imageData);
            mapImage.setImage(image);
        } catch(Exception e) {
            System.out.println("Error " + e);
        }*/
        LatLng coord = null;
        try {
            //coord = geoCodeAddress(address);
            Image image = getImage(address);
            mapImage.setImage(image);
        } catch(Exception e) {
            System.out.println("Error " + e);
        }

    }

}