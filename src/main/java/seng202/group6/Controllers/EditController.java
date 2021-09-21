package seng202.group6.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;
import seng202.group6.Services.SQLiteDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        Crime viewedCrime = MasterController.currentCrime;

        if (!isNewCrime) {
            caseNumber.setText(viewedCrime.getCaseNumber());
            date.setValue(viewedCrime.getDate().toLocalDate());
            block.setText(viewedCrime.getBlock());
            IUCR.setText(viewedCrime.getIucr());
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
        } else {
            caseNumber.setDisable(false);
            caseNumber.setEditable(true);
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

        currentCrime.setDate(LocalDateTime.of(date.getValue(), LocalTime.MIDNIGHT));
        MasterController.currentCrime.setBeat(Integer.parseInt(beat.getText()));
        MasterController.currentCrime.setWard(Integer.parseInt(ward.getText()));
        MasterController.currentCrime.setCaseNumber(caseNumber.getText());
        MasterController.currentCrime.setBlock(block.getText());
        MasterController.currentCrime.setIucr(IUCR.getText());
        MasterController.currentCrime.setPrimaryDescription(primaryDescription.getText());
        MasterController.currentCrime.setSecondaryDescription(secondaryDescription.getText());
        MasterController.currentCrime.setLocationDescription(location.getText());
        MasterController.currentCrime.setArrest(arrest.isSelected());
        MasterController.currentCrime.setDomestic(domestic.isSelected());
        MasterController.currentCrime.setFBI(fbiCD.getText());
        MasterController.currentCrime.setBeat(beatNum);
        MasterController.currentCrime.setWard(wardNum);
        MasterController.currentCrime.setLatitude(Double.parseDouble(latitude.getText()));
        MasterController.currentCrime.setLongitude(Double.parseDouble(longitude.getText()));


        try {
            if (isNewCrime) {
                SQLiteDatabase.insertIntoTable("Crimes", MasterController.currentCrime);
                crimeData.add(MasterController.currentCrime);
            } else {
                SQLiteDatabase.updateInTable("Crimes", currentCrime);
            }

            SQLiteDatabase.endTransaction();

            changeToDataScreen();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (SQLException e) {
            (new Alert(Alert.AlertType.ERROR, "Invalid Crime - could not add to database")).show();
        }





    }



}
