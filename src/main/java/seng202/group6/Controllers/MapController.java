package seng202.group6.Controllers;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seng202.group6.Models.Crime;
import seng202.group6.Services.DynamicMapService;
import seng202.group6.Services.SQLiteDatabase;
import seng202.group6.Services.StaticMapService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for map screen in the user interface, associated with homeScreen.fxml.
 * Is a child class of MasterController
 */

public class MapController extends MasterController implements Initializable {

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
    private Button getRemoveMarkersButton;

    @FXML
    private Pane mapPane;

    @FXML
    private Button viewMapButton;

    @FXML
    private TextField addressField;

    @FXML
    private Text noAddressText;

    @FXML
    private Button removeMarkersButton;

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

   /* public void clickViewMap(ActionEvent event) {
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
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populates crimeData arraylist from database
        //Probably a cleaner way to make this work, so it isn't called twice but this works for now
        try {
            crimeData = SQLiteDatabase.convertResultSet(SQLiteDatabase.selectAllFromTable("Crimes"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*WebEngine webEngine = mapView.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadMarkers(webEngine);
            }
        });

        File file = new File("src/main/resources/HTML/EmbedMaps.html");
        try {
            webEngine.load(file.toURI().toString());
        } catch (Exception e) {
            System.out.println("Error in clickViewMap: " + e);
        }*/
        WebView mapView = DynamicMapService.getMapView();
        WebEngine webEngine = mapView.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {



            }
        });
        mapView.setMaxSize(mapPane.getPrefWidth(), mapPane.getPrefHeight());
        mapPane.getChildren().add(mapView);
    }

    /*public void addMarkers(ActionEvent event) {
        DynamicMapService.loadMarkers(super.crimeData);
    }*/

    public void addMarkers(ActionEvent event) {
        DynamicMapService.loadMarker(super.crimeData);
    }

    public void removeMarkers(ActionEvent event) {
        DynamicMapService.removeMarkers();
    }




}