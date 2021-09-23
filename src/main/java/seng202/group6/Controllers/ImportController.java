package seng202.group6.Controllers;

import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import seng202.group6.Controllers.DataController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.group6.Models.Crime;
import seng202.group6.Services.ParserService;
import seng202.group6.Services.SQLiteDatabase;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for import screen in user interface, is associated with importScreen.fxml.
 * Is a child class of MasterController
 */

public class ImportController extends MasterController implements Initializable {

    @FXML
    private Button homeButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button graphButton;

    @FXML
    private Button importNew;

    @FXML
    private Button addToTable;

    @FXML
    private Button deleteTable;

    @FXML
    private Button changeDataSet;

    @FXML
    private Button exportData;

    @FXML
    private Label noDataSelected;

    @FXML
    private ListView tableList;

    @FXML
    private Label uploadSuccess;

    @FXML
    private Label currentTableText;

    private ArrayList<String> tableNames = new ArrayList<>();
    public static String currentTable; //current table that is being viewed


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        homeButton.setFocusTraversable(false);
        mapButton.setFocusTraversable(false);
        dataButton.setFocusTraversable(false);
        graphButton.setFocusTraversable(false);

        currentTableText.setText("You are currently viewing: " + currentTable);

        try {
            ResultSet sqlTables = SQLiteDatabase.getTableNames();
            while (sqlTables.next()) {
                tableNames.add(sqlTables.getString(1));
            }
            tableList.setItems(FXCollections.observableArrayList(tableNames));
            sqlTables.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String getFirstTable() throws SQLException {

        ResultSet tableNames = SQLiteDatabase.getTableNames();
        String toReturn = tableNames.getString(1);
        tableNames.close();
        return toReturn;

    }


    /**
     * Method to call change to home screen method in MasterController when the home button
     * is clicked
     * @throws IOException
     */
    
    public void clickHome() throws IOException {
        changeToHomeScreen();
    }

    /**
     * Method to call change to map screen method in MasterController when the map button
     * is clicked
     * @throws IOException
     */
    
    public void clickMap() throws IOException {
        changeToMapScreen();
    }

    /**
     * Method to call change to data screen method in MasterController when the data button
     * is clicked
     * @throws IOException
     */

    public void clickData() throws IOException {
        changeToDataScreen();
    }

    /**
     * Method to call change to graph screen method in MasterController when the graph button
     * is clicked
     * @throws IOException
     */
    public void clickGraph() throws IOException {
        changeToGraphScreen();
    }

    public void uploadFile(String tableName) throws CsvValidationException, SQLException, IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open crime data file");
        int recordsOmitted = 0;
        boolean validUpload = true;
        File crimeFile = fileChooser.showOpenDialog(stage);
        if (crimeFile == null) {
            validUpload = false;
        } else {
            //Creating new tables and giving them a name should be done here

            recordsOmitted = ParserService.csvToDatabase(crimeFile, tableName); //TODO: deal with thrown exceptions

            //filteredCrimeData = crimeData;

            // need to make method to check if file is csv format and if they actually selected a file
            // also need to get checks for correct format in parser
        }

        MasterController.populateCrimeArray(tableName);

        if (validUpload) {
            uploadSuccess.setText("File uploaded successfully. " + recordsOmitted + " records omitted.");
            uploadSuccess.setVisible(true);
            currentTable = tableName;
            currentTableText.setText("You are currently viewing: " + currentTable);
        }
    }


    public void clickImportNew()  {

        TextInputDialog txtDlg = new TextInputDialog();
        txtDlg.setHeaderText("Please enter the name of the table you want to create");
        txtDlg.setTitle("Create New Table");
        txtDlg.setContentText("Table name:");

        // returns String optional
        Optional<String> tableName = txtDlg.showAndWait();

        if (!tableName.isEmpty()) {
            if (tableName.equals("")) {
                (new Alert(Alert.AlertType.ERROR, "Invalid table name: cannot be empty")).show();
            } else {
                try {
                    SQLiteDatabase.createTable(tableName.get());
                    uploadFile(tableName.get());
                    tableNames.add(tableName.get());
                    tableList.setItems(FXCollections.observableArrayList(tableNames));
                } catch (SQLException e) {
                    (new Alert(Alert.AlertType.ERROR, "Unable to add to database")).show();
                    e.printStackTrace();
                } catch (CsvValidationException e) {
                    (new Alert(Alert.AlertType.ERROR, "Unable to read file")).show();
                } catch (IOException e) {
                    (new Alert(Alert.AlertType.ERROR, "File not found")).show();
                }
            }
        }

    }


    public void clickAddData()  {

        String tableName = (String) tableList.getSelectionModel().getSelectedItem();
        if (tableName != null) {
            try {
                uploadFile(tableName);
            } catch (SQLException e) {
                (new Alert(Alert.AlertType.ERROR, "Unable to add to database")).show();
            } catch (CsvValidationException e) {
                (new Alert(Alert.AlertType.ERROR, "Unable to read file")).show();
            } catch (IOException e) {
                (new Alert(Alert.AlertType.ERROR, "File not found")).show();
            }
        } else {
            noDataSelected.setVisible(true);
        }
    }

    public void clickDeleteTable() {

        String tableName = (String) tableList.getSelectionModel().getSelectedItem();

        if (tableName != null) {

            try {

                SQLiteDatabase.dropTable(tableName);
                tableNames.remove(tableName);
                tableList.setItems(FXCollections.observableArrayList(tableNames));

                try {
                    MasterController.populateCrimeArray(getFirstTable());
                    currentTableText.setText("You are currently viewing: " + getFirstTable());
                } catch (SQLException e) {
                    crimeData = new ArrayList<>(); //if there is no more tables in database, sets data to empty list
                }

            } catch (SQLException e){
                (new Alert(Alert.AlertType.ERROR, "Unable to delete from database")).show();
                e.printStackTrace();
            }


        } else {
            noDataSelected.setVisible(true);
        }

    }

    public void clickChangeData() {
        String tableName = (String) tableList.getSelectionModel().getSelectedItem();
        if (tableName != null) {
            MasterController.populateCrimeArray(tableName);
            currentTable = tableName;
            currentTableText.setText("You are currently viewing: " + currentTable);
        } else {
            noDataSelected.setVisible(true);
        }
    }

    public void clickExport() {

    }





}
