package seng202.group6.Controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group6.Models.CrimeFrequency;

import seng202.group6.Models.TimeFrequency;

import seng202.group6.Models.TimeFrequency;
import seng202.group6.Services.SQLiteDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static seng202.group6.Services.GraphService.*;
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
    private PieChart pieChart;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    public NumberAxis xAxis;

    private ArrayList<TimeFrequency> timeFrequencyData = new ArrayList<TimeFrequency>();

    private int typeOf = 0; //0 for HourOfDay, 1 for DayOfWeek, 2 for MonthOfYear

    private boolean flag = false;

    private ArrayList<CrimeFrequency> data = new ArrayList<>();

    private XYChart.Series<Number, Number> oldData = null;

    private boolean dataUpdate = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //lineChart.getData().clear(); //wonder whether needed anymore

        flag = false;

        try {
            clickApplyChart();
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
     * Method to call change to hourOfDayData screen method in MasterController when the hourOfDayData button
     * is clicked
     * @throws IOException
     */
    public void clickData() throws IOException {
        changeToDataScreen();
    }

    /**
     * Method to make a line chart be created on the graph, it checks if the chart it is being requested to make has
     * already been made and if so it doesn't make it
     * @throws IOException
     */
    private void clickApplyChart() throws IOException {
        XYChart.Series<Number, Number> series = getChartData(typeOf, crimeData);
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
     *
     */
    public void clickDay() throws IOException {
        pieChart.setVisible(false);
        xAxis.forceZeroInRangeProperty();
        typeOf = 0;
        xAxis.setLabel("Time of Day");
        clickApplyChart();
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
     * Method which shows the graph of the frequency of crimes by day of week
     * @throws IOException
     */
    public void clickWeek() throws IOException {
        pieChart.setVisible(false);
        typeOf = 1;
        xAxis.setLabel("Time of Week");
        clickApplyChart();
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
     * Method which shows the graph of frequency of crime per month of year
     */
    public void clickYear() throws IOException {
        pieChart.setVisible(false);
        typeOf = 2;
        xAxis.setLabel("Time of Year");
        clickApplyChart();
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
     * Method which causes the graph screen to display a pie chart showing the breakdown of crimes by type of crime
     */
    public void clickPie() {
        lineChart.setVisible(false);
        data = rankedTypeList(crimeData);
        ObservableList<PieChart.Data> pcd = FXCollections.observableArrayList();
        for (CrimeFrequency crime : data) {
            pcd.add(new PieChart.Data(crime.getCrime(), crime.getFrequency()));
        }
        if (!flag) {
            pieChart.setData(pcd);
        }
        pieChart.setVisible(true);
        flag = true;
    }

}
