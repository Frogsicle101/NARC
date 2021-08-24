package seng202.group6.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group6.Models.Crime;
import seng202.group6.Models.Date;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DataController extends MasterController {

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

    public void initialize(URL url, ResourceBundle rb) {

        caseNumColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("Case Number"));
        primaryDescColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("Primary Description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("Location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Crime, Date>("Date"));

        tableView.setItems(getCrimes());

    }

    public ObservableList<Crime> getCrimes() {
        return getCrimeData();
    }

    public void clickHome(ActionEvent event) throws IOException {
        changeToHomeScreen(event);
    }

    public void clickMap(ActionEvent event) throws IOException {
        changeToMapScreen(event);
    }

    public void clickImport(ActionEvent event) throws IOException {
        changeToImportScreen(event);
    }


}
