package seng202.group6.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import seng202.group6.Models.Crime;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for viewing detailed view of specific crime, associated with
 * viewCrimeScreen.fxml. Is a child class of MasterController
 */

public class ViewController extends MasterController implements Initializable {

    @FXML
    private Text caseNumber;

    @FXML
    private Text date;

    @FXML
    private Text block;

    @FXML
    private Text IUCR;

    @FXML
    private Text primaryDescription;

    @FXML
    private Text secondaryDescription;

    @FXML
    private Text location;

    @FXML
    private Text arrest;

    @FXML
    private Text domestic;

    @FXML
    private Text beat;

    @FXML
    private Text ward;

    @FXML
    private Text fbiCD;

    @FXML
    private Text latitude;

    @FXML
    private Text longitude;

    @FXML
    private WebView mapView;

    /**
     * Method to initialize view scene, sets all the specific text fields to
     * their correlating values in the specific crime given. Specific crime is
     * taken from crimeToView in MasterController class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Crime viewedCrime = MasterController.currentCrime;

        caseNumber.setText(viewedCrime.getCaseNumber());
        date.setText(viewedCrime.getDate().toString());
        block.setText(viewedCrime.getBlock());
        IUCR.setText(viewedCrime.getIucr());
        primaryDescription.setText(viewedCrime.getPrimaryDescription());
        secondaryDescription.setText(viewedCrime.getSecondaryDescription());
        location.setText(viewedCrime.getLocationDescription());
        arrest.setText(Boolean.toString(viewedCrime.isArrest()));
        domestic.setText(Boolean.toString(viewedCrime.isDomestic()));
        beat.setText(Integer.toString(viewedCrime.getBeat()));
        ward.setText(Integer.toString(viewedCrime.getWard()));
        fbiCD.setText(viewedCrime.getFBI());
        latitude.setText(Double.toString(viewedCrime.getLatitude()));
        longitude.setText(Double.toString(viewedCrime.getLongitude()));

    }



}
