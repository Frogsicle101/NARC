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
import java.util.ResourceBundle;

import static seng202.group6.Services.Rank.rankedTypeList;

public class ViewRankScreenController extends MasterController implements Initializable {
    private ArrayList<CrimeFrequency> data;

    @FXML
    protected TableView<CrimeFrequency> tableView;

    @FXML
    private TableColumn<CrimeFrequency, String> typeCrimeColumn;

    @FXML
    private TableColumn<CrimeFrequency, String> frequencyColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //assume not null (fix later?)
        data = rankedTypeList(filteredCrimeData);
        typeCrimeColumn.setCellValueFactory(new PropertyValueFactory<CrimeFrequency, String>("Type of crime"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<CrimeFrequency, String>("Frequency of crime"));
        tableView.setItems(FXCollections.observableArrayList(data));



    }
}
