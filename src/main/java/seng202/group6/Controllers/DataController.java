package seng202.group6.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import seng202.group6.Models.Crime;
import seng202.group6.Models.Date;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for data screen in user interface, associated with dataScreen.fxml.
 * Is a child class of DataController
 */

public class DataController extends MasterController implements Initializable {

    @FXML
    private Button homeButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button importButton;

    @FXML
    private TableView<Crime> tableView;

    @FXML
    private TableColumn<Crime, String> caseNumColumn;

    @FXML
    private TableColumn<Crime, String> primaryDescColumn;

    @FXML
    private TableColumn<Crime, String> locationColumn;

    @FXML
    private TableColumn<Crime, Date> dateColumn;

    @FXML
    private Button viewCrime;

    @FXML
    private Text noDataText;

    @FXML
    private Text notSelectedText;

    /**
    * Method to initialize data scene, checks if there has been data imported first,
     * if there has it will show a data with all crimes in a table, and give an optional button
     * to click to view a more detailed view of a specific crime. An error message is shown
     * rather than the table and button if there has been no data imported
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (MasterController.crimeData.size() > 0) {

            noDataText.setVisible(false);
            tableView.setVisible(true);
            viewCrime.setVisible(true);

            caseNumColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("caseNumber"));
            primaryDescColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("primaryDescription"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("locationDescription"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Crime, Date>("date"));

            tableView.setItems(FXCollections.observableArrayList(MasterController.crimeData));
        } else {
            noDataText.setVisible(true);
            tableView.setVisible(false);
            viewCrime.setVisible(false);
        }

    }


    /**
     * Method to view detailed description of a specific crime, checks if a crime is selected
     * from the data table and returns error message if not. Calls function from MasterController
     * if a crime has been selected
     * @param event Button click event when view more info button is clicked
     * @throws IOException
     */
    public void selectCrime(ActionEvent event) throws IOException {
        Crime crime = tableView.getSelectionModel().getSelectedItem();
        if (crime != null) {
            notSelectedText.setVisible(false);
            launchViewScreen(event, crime);
        } else {
            notSelectedText.setVisible(true);
        }

    }

    /**
     * Method to call change to home screen method in MasterController when the home button
     * is clicked
     * @param event Button click event when home button is clicked
     * @throws IOException
     */

    public void clickHome(ActionEvent event) throws IOException {
        changeToHomeScreen(event);
    }

    /**
     * Method to call change to map screen method in MasterController when the map button
     * is clicked
     * @param event Button click event when map button is clicked
     * @throws IOException
     */

    public void clickMap(ActionEvent event) throws IOException {
        changeToMapScreen(event);
    }

    /**
     * Method to call change to import screen method in MasterController when the import button
     * is clicked
     * @param event Button click event when import button is clicked
     * @throws IOException
     */

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen(event);
    }



}
