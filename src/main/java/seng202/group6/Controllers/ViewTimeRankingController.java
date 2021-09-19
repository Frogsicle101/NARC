package seng202.group6.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group6.Models.AreaFrequency;
import seng202.group6.Models.Crime;
import seng202.group6.Models.CrimeFrequency;
import seng202.group6.Models.TimeFrequency;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

import static seng202.group6.Services.Rank.*;

public class ViewTimeRankingController extends MasterController implements Initializable {
    private ArrayList<TimeFrequency> data = new ArrayList<TimeFrequency>();


    @FXML
    protected TableView<TimeFrequency> tableView;

    @FXML
    private TableColumn<TimeFrequency, String> TimeColumn;

    @FXML
    private TableColumn<TimeFrequency, String> frequencyColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (filteredCrimeData.size() != 0) {
            data = rankedTimeList(filteredCrimeData);
        } else {
            data = rankedTimeList(crimeData);
        }
        TimeColumn.setCellValueFactory(new PropertyValueFactory<TimeFrequency, String>("hourOfTheDay"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<TimeFrequency, String>("frequency"));
        tableView.setItems(FXCollections.observableArrayList(data));
    }
}

