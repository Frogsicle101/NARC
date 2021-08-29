package seng202.group6.Controllers;

import com.google.maps.model.LatLng;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

    @FXML
    private Text noAddressText;

    public void clickHome(ActionEvent event) throws IOException {
        changeToHomeScreen(event);
    }

    public void clickData(ActionEvent event) throws IOException {
        changeToDataScreen(event);
    }

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen(event);
    }

    public void clickViewMap(ActionEvent event) {
        String address = addressField.getText();
        System.out.println(address.isEmpty());
        if (!address.isEmpty()) {
            noAddressText.setVisible(false);
            LatLng coord = null;
            try {
                //coord = geoCodeAddress(address);
                Image image = getStaticMap(address);
                mapImage.setImage(image);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } else {
            noAddressText.setVisible(true);
        }
    }

}