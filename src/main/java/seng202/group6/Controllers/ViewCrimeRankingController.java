package seng202.group6.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group6.Models.Crime;
import seng202.group6.Models.CrimeFrequency;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

import static seng202.group6.Services.Rank.rankedTypeList;

public class ViewCrimeRankingController extends MasterController implements Initializable {
    private ArrayList<CrimeFrequency> data = new ArrayList<>();

    @FXML
    protected TableView<CrimeFrequency> tableView;

    @FXML
    private TableColumn<CrimeFrequency, String> typeCrimeColumn;

    @FXML
    private TableColumn<CrimeFrequency, String> frequencyColumn;

    /**
     * Method initializes the table to be viewed and feeds it the data needed after naming the columns appropriately
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = rankedTypeList(crimeData);
        typeCrimeColumn.setCellValueFactory(new PropertyValueFactory<CrimeFrequency, String>("crime"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<CrimeFrequency, String>("frequency"));
        tableView.setItems(FXCollections.observableArrayList(data));



    }
}
