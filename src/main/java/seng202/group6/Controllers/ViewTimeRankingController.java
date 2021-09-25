package seng202.group6.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group6.Models.TimeFrequency;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static seng202.group6.Services.Rank.*;

/**
 * Controller is for viewing the ranking of crimes per hour of the day
 */

public class ViewTimeRankingController extends MasterController implements Initializable {
    private ArrayList<TimeFrequency> data = new ArrayList<TimeFrequency>();


    @FXML
    protected TableView<TimeFrequency> tableView;

    @FXML
    private TableColumn<TimeFrequency, String> timeColumn;

    @FXML
    private TableColumn<TimeFrequency, String> frequencyColumn;

    /**
     * Method initializes the table to be viewed and feed's it the data needed after naming the columns appropriately
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = rankedTimeList(crimeData, 0);
        timeColumn.setCellValueFactory(new PropertyValueFactory<TimeFrequency, String>("timeString"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<TimeFrequency, String>("frequency"));
        tableView.setItems(FXCollections.observableArrayList(data));
    }
}

