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
    private Text notSelectedText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableView.setVisible(true);
        caseNumColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("caseNumber"));
        primaryDescColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("primaryDescription"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Crime, String>("locationDescription"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Crime, Date>("date"));

        tableView.setItems(MasterController.crimeData);
    }

    public void selectCrime(ActionEvent event) throws IOException {
        Crime crime = tableView.getSelectionModel().getSelectedItem();
        if (crime != null) {
            notSelectedText.setVisible(false);
            launchViewScreen(event, crime);
        } else {
            notSelectedText.setVisible(true);
        }

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
