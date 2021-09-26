package seng202.group6.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group6.Models.Frequency;

import java.util.ArrayList;

public class ViewRankingController {

    @FXML
    protected TableView<Frequency> tableView;

    @FXML
    private TableColumn<Frequency, String> valueColumn;

    @FXML
    private TableColumn<Frequency, String> frequencyColumn;

    /**
     * Method initializes the table to be viewed and feeds it the data needed after naming the columns appropriately
     */
    public void initialize(String title, ArrayList<Frequency> data) {

        tableView.setFocusTraversable(false);
        valueColumn.setText(title);

        if (title.equals("Hour")) {
            valueColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        } else {
            valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        }

        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        tableView.setItems(FXCollections.observableArrayList(data));



    }


}
