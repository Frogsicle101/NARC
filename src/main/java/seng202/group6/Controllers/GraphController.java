package seng202.group6.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import seng202.group6.Models.Frequency;
import seng202.group6.Models.TimeType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static seng202.group6.Services.GraphService.*;
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
    private PieChart pieChart;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    public NumberAxis xAxis;

    private TimeType timeType = TimeType.HOUR_OF_DAY;

    private boolean flag = false;

    private XYChart.Series<Number, Number> oldData = null;

    private boolean dataUpdate = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //lineChart.getData().clear(); //wonder whether needed anymore

        flag = false;

        try {
            applyChart();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapButton.setFocusTraversable(false);
        dataButton.setFocusTraversable(false);
        importButton.setFocusTraversable(false);
        homeButton.setFocusTraversable(false);

        pieChart.setVisible(false);

        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, ":00"));

    }

    /**
     * Changes to home screen using method in MasterController when the home button is clicked
     */
    public void clickHome() throws IOException {
        changeToHomeScreen();
    }

    /**
     * Changes to map screen using method in MasterController when the map button is clicked
     */
    public void clickMap() throws IOException {
        changeToMapScreen();
    }

    /**
     * Changes to import screen using method in MasterController when the import button is clicked
     */
    public void clickImport() throws IOException {
        changeToImportScreen();
    }

    /**
     * Change to data screen using method in MasterController when the data button is clicked
     */
    public void clickData() throws IOException {
        changeToDataScreen();
    }

    /**
     * Method to make a line chart be created on the graph, it checks if the chart it is being requested to make has
     * already been made and if so it doesn't make it
     */
    private void applyChart() throws IOException {
        XYChart.Series<Number, Number> series = getChartData(timeType, crimeData);
        if (oldData == null || series.getData().size() != oldData.getData().size()) {
            oldData = series;
            lineChart.getData().clear();
            lineChart.getData().add(series);
            xAxis.setUpperBound(maxValue -1);
            xAxis.setLowerBound(minValue);
            dataUpdate = true;
        }
    }

    /**
     * Set's the displayed graph to show crimes by hour of the day
     */
    public void clickDay() throws IOException {
        pieChart.setVisible(false);
        xAxis.forceZeroInRangeProperty();
        timeType = TimeType.HOUR_OF_DAY;
        xAxis.setLabel("Time of Day");
        applyChart();
        if (dataUpdate) {
            int i;
            for (i = 0; i < 24; i++) {
                //todo
                xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, ":00"));
            }

        }
        lineChart.setVisible(true);
        dataUpdate = false;

    }

    /**
     * Displays a graph of the frequency of crimes by day of the week
     */
    public void clickWeek() throws IOException {
        pieChart.setVisible(false);
        timeType = TimeType.DAY_OF_WEEK;
        xAxis.setLabel("Time of Week");
        applyChart();
        if (dataUpdate) {
            String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            int i;
            for (i = 0; i < 7; i++) {
                //todo
                xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, null));
            }

        }
        lineChart.setVisible(true);
        dataUpdate = false;
    }


    /**
     * Displays a graph of frequency of crime per month of the year
     */
    public void clickYear() throws IOException {
        pieChart.setVisible(false);
        timeType = TimeType.MONTH_OF_YEAR;
        xAxis.setLabel("Time of Year");
        applyChart();
        if (dataUpdate) {

            int i;
            for (i = 0; i < 12; i++) {
                //todo
                xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, null));
            }

        }
        dataUpdate = false;
        lineChart.setVisible(true);
    }


    /**
     * Causes the graph screen to display a pie chart showing the breakdown of crimes by type of crime
     */
    public void clickPie() {
        lineChart.setVisible(false);
        ArrayList<Frequency> data = rankedTypeList(crimeData);
        ObservableList<PieChart.Data> pcd = FXCollections.observableArrayList();
        for (Frequency crimeType : data) {
            pcd.add(new PieChart.Data(crimeType.getValue(), crimeType.getCount()));
        }
        if (!flag) {
            pieChart.setData(pcd);
        }
        pieChart.setVisible(true);
        flag = true;
    }

}
