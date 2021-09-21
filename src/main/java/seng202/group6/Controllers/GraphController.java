package seng202.group6.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import seng202.group6.Models.CrimeFrequency;
import seng202.group6.Models.TimeFrequency;
import seng202.group6.Services.SQLiteDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
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
    private LineChart<Number, Number> lineChart;

    private ArrayList<TimeFrequency> data = new ArrayList<TimeFrequency>();

    @FXML
    public NumberAxis xAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lineChart.getData().clear();
        xAxis.setUpperBound(23);
        xAxis.setLowerBound(0);
        xAxis.forceZeroInRangeProperty();
        xAxis.setTickUnit(1);
        xAxis.setTickLabelGap(1);
        try {
            clickApplyChart();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void clickApplyChart() throws IOException {
       if ((data.size() == 0 && crimeData.size() == 0) || (data.size() != rankedTimeList(filteredCrimeData).size() && filteredCrimeData.size() != 0) || (
               data.size() != rankedTimeList(crimeData).size() && crimeData.size() != 0 && filteredCrimeData.size() == 0)) {
            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            lineChart.getData().clear();
            if (filteredCrimeData.size() != 0) {
                data = rankedTimeList(filteredCrimeData); //DO we want this from filtered data or
            } else {
                if (crimeData.size() != 0) {
                    data = rankedTimeList(crimeData);
                } else {
                    try {
                        data = rankedTimeList(SQLiteDatabase.convertResultSet(SQLiteDatabase.selectAllFromTable("Crimes")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            data.sort(Comparator.comparing(TimeFrequency::getHourOfTheDay));
            for (int i = 0; i < 24; i++) {
                boolean found = false;
                int index = 0;
                for (TimeFrequency time : data) {
                    if (time.getHourOfTheDay() == i) {
                        found = true;
                        index = data.indexOf(time);
                    }
                }
                if (found) {
                    series.getData().add(new XYChart.Data<Number, Number>(data.get(index).getHourOfTheDay(), data.get(index).getFrequency()));
                } else {
                    series.getData().add(new XYChart.Data<Number, Number>(i, 0));
                }

            }

            series.setName("Crime Frequency Over Day");
            lineChart.getData().add(series);

        }
    }

}
