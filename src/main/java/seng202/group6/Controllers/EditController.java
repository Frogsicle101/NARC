package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for editing the details of or adding a new crime, associated with
 * editCrimeScreen.fxml. Is a child class of MasterController
 */

public class EditController extends MasterController implements Initializable {


    protected static boolean isNewCrime;

    @FXML
    private TextField caseNumber;

    @FXML
    private DatePicker date;

    @FXML
    private TextField block;

    @FXML
    private TextField IUCR;

    @FXML
    private TextField primaryDescription;

    @FXML
    private TextField secondaryDescription;

    @FXML
    private TextField location;

    @FXML
    private CheckBox arrest;

    @FXML
    private CheckBox domestic;

    @FXML
    private TextField beat;

    @FXML
    private TextField ward;

    @FXML
    private TextField fbiCD;

    @FXML
    private TextField latitude;

    @FXML
    private TextField longitude;

    @FXML
    private Button applyButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Crime viewedCrime = MasterController.crimeToView;

        if (!isNewCrime) {
            caseNumber.setText(viewedCrime.getCaseNumber());
            // date.setDate()
            block.setText(viewedCrime.getBlock());
            IUCR.setText(viewedCrime.getIUCR());
            primaryDescription.setText(viewedCrime.getPrimaryDescription());
            secondaryDescription.setText(viewedCrime.getSecondaryDescription());
            location.setText(viewedCrime.getLocationDescription());
            arrest.setSelected(viewedCrime.isArrest());
            domestic.setSelected(viewedCrime.isDomestic());
            beat.setText(Integer.toString(viewedCrime.getBeat()));
            ward.setText(Integer.toString(viewedCrime.getWard()));
            fbiCD.setText(viewedCrime.getFBI());
            latitude.setText(Double.toString(viewedCrime.getLatitude()));
            longitude.setText(Double.toString(viewedCrime.getLongitude()));
        }


    }

    public void clickApply(ActionEvent event) throws IOException {

        int beatNum;
        int wardNum;
        try {
            beatNum = Integer.parseInt(beat.getText());
            wardNum = Integer.parseInt(ward.getText());

        } catch (NumberFormatException e) {
            (new Alert(Alert.AlertType.ERROR, "Beat or Ward formatted incorrectly")).show();
            return;
        }

        MasterController.crimeToView.setBeat(Integer.parseInt(beat.getText()));
        MasterController.crimeToView.setWard(Integer.parseInt(ward.getText()));
        MasterController.crimeToView.setCaseNumber(caseNumber.getText());
        //TODO: Date stuff
        MasterController.crimeToView.setBlock(block.getText());
        MasterController.crimeToView.setIUCR(IUCR.getText());
        MasterController.crimeToView.setPrimaryDescription(primaryDescription.getText());
        MasterController.crimeToView.setSecondaryDescription(secondaryDescription.getText());
        MasterController.crimeToView.setLocationDescription(location.getText());
        MasterController.crimeToView.setArrest(arrest.isSelected());
        MasterController.crimeToView.setDomestic(domestic.isSelected());
        MasterController.crimeToView.setFBI(fbiCD.getText());
        MasterController.crimeToView.setBeat(beatNum);
        MasterController.crimeToView.setWard(wardNum);
        MasterController.crimeToView.setLatitude(Double.parseDouble(latitude.getText()));
        MasterController.crimeToView.setLongitude(Double.parseDouble(longitude.getText()));

        if (isNewCrime) {
            MasterController.crimeData.add(MasterController.crimeToView);
            System.out.println(crimeToView.getPrimaryDescription());
        }

        changeToDataScreen();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();



    }



}
