package seng202.group6.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import seng202.group6.Models.Crime;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Crime viewedCrime = MasterController.crimeToView;

        caseNumber.setText(viewedCrime.getCaseNumber());
        // date.setText(viewedCrime.getDate());
        block.setText(viewedCrime.getBlock());
        IUCR.setText(viewedCrime.getIUCR());
        primaryDescription.setText(viewedCrime.getPrimaryDescription());
        secondaryDescription.setText(viewedCrime.getSecondaryDescription());
        location.setText(viewedCrime.getLocationDescription());
        arrest.setText(Boolean.toString(viewedCrime.isArrest()));
        domestic.setText(Boolean.toString(viewedCrime.isDomestic()));
        beat.setText(Integer.toString(viewedCrime.getBeat()));
        ward.setText(Integer.toString(viewedCrime.getWard()));
        fbiCD.setText(viewedCrime.getFBI());
        latitude.setText(viewedCrime.getLatitude());
        longitude.setText(viewedCrime.getLongitude());

    }



}
