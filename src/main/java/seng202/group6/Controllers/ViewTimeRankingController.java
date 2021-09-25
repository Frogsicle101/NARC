package seng202.group6.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group6.Models.FrequencyObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static seng202.group6.Services.Rank.*;

public class ViewTimeRankingController extends MasterController implements Initializable {
    private ArrayList<FrequencyObject> data = new ArrayList<FrequencyObject>();


    @FXML
    protected TableView<FrequencyObject> tableView;

    @FXML
    private TableColumn<FrequencyObject, String> timeColumn;

    @FXML
    private TableColumn<FrequencyObject, String> frequencyColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = rankedTimeList(crimeData, 0);
        timeColumn.setCellValueFactory(new PropertyValueFactory<FrequencyObject, String>("timeString"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<FrequencyObject, String>("frequency"));
        tableView.setItems(FXCollections.observableArrayList(data));
    }
}

