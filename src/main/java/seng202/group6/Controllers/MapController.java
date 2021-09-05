package seng202.group6.Controllers;

import com.google.maps.model.LatLng;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import seng202.group6.Models.Crime;
import seng202.group6.Services.MapService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for map screen in the user interface, associated with homeScreen.fxml.
 * Is a child class of MasterController
 */

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

    /**
     * Method to call change to home screen method in MasterController when the home button
     * is clicked
     * @param event Button click event when home button is clicked
     * @throws IOException
     */

    public void clickHome(ActionEvent event) throws IOException {
        changeToHomeScreen();
    }

    /**
     * Method to call change to data screen method in MasterController when the data button
     * is clicked
     * @param event Button click event when data button is clicked
     * @throws IOException
     */

    public void clickData(ActionEvent event) throws IOException {
        changeToDataScreen();
    }

    /**
     * Method to call change to data screen method in MasterController when the data button
     * is clicked
     * @param event Button click event when data button is clicked
     * @throws IOException
     */

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen();
    }

    public void clickViewMap(ActionEvent event) {
        String address = addressField.getText();
        //System.out.println(address.isEmpty());
        if (!address.isEmpty()) {
            noAddressText.setVisible(false);
            try {
                Image image = MapService.getStaticMap(address, super.crimeData);
                mapImage.setImage(image);
            } catch (Exception e) {
                System.out.println("Error opening Image: " + e);
            }
        } else {
            noAddressText.setVisible(true);
        }
    }

}