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

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

import static seng202.group6.Services.Rank.rankedAreaList;
import static seng202.group6.Services.Rank.rankedTypeList;

public class ViewAreaRankingController extends MasterController implements Initializable {
    private ArrayList<AreaFrequency> data = new ArrayList<>();

    @FXML
    protected TableView<AreaFrequency> tableView;

    @FXML
    private TableColumn<AreaFrequency, String> AreaColumn;

    @FXML
    private TableColumn<AreaFrequency, String> frequencyColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = rankedAreaList(crimeData);
        AreaColumn.setCellValueFactory(new PropertyValueFactory<AreaFrequency, String>("area"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<AreaFrequency, String>("frequency"));
        tableView.setItems(FXCollections.observableArrayList(data));



    }
}
