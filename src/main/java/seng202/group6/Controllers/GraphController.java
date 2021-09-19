package seng202.group6.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import seng202.group6.Models.TimeFrequency;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static seng202.group6.Services.Rank.rankedTimeList;
import static seng202.group6.Services.Rank.rankedTypeList;

/**
 * Controller class for Graph screen in user interface, associated with graphScreen.fxml.
 */

public class GraphController extends MasterController implements Initializable {

    @FXML
    private Button homeButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button importButton;

    @FXML
    private Button applyChartButton;

    @FXML
    private LineChart<String, Number> lineChart;

    private ArrayList<TimeFrequency> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mapButton.setFocusTraversable(false);
        dataButton.setFocusTraversable(false);
        importButton.setFocusTraversable(false);
        homeButton.setFocusTraversable(false);

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
     * Method to call change to import screen method in MasterController when the import button
     * is clicked
     * @throws IOException
     */

    public void clickImport() throws IOException {
        changeToImportScreen();
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
     * Method to make a line chart be created on the graph
     * @throws IOException
     */
    public void clickApplyChart() throws IOException {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        if (filteredCrimeData.size() != 0) {
            data = rankedTimeList(filteredCrimeData); //DO we want this from filtered data or
        } else {
            data = rankedTimeList(crimeData);
        }
        data.sort(Comparator.comparing(TimeFrequency::getHourOfTheDay));
        int a = 0;
        for (int i=0; i < 24; i++) {
            series.getData().add(new XYChart.Data<String, Number>(valueOf(data.get(i).getHourOfTheDay()), data.get(i).getFrequency()));
            a++;
        }
        series.setName("Crime Frequency Over Day");
        lineChart.getData().add(series);

    }

}
