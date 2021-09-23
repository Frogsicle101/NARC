package seng202.group6.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import seng202.group6.Models.DayOfWeekFrequency;
import seng202.group6.Models.FrequencyObject;
import seng202.group6.Models.MonthFrequency;
import seng202.group6.Services.SQLiteDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static seng202.group6.Services.Rank.rankedTimeList;

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

    private ArrayList<FrequencyObject> timeFrequencyData = new ArrayList<FrequencyObject>();

    @FXML
    public NumberAxis xAxis;

    private int typeOf = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lineChart.getData().clear();

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
     * Method to call change to hourOfDayData screen method in MasterController when the hourOfDayData button
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
        int maxValue = 0;
        int minValue = 0;
        switch (typeOf) {
            case 0:
                maxValue = 24;
                break;
            case 1:
                maxValue = 8;
                minValue = 1;
                break;
            case 2:
                maxValue = 13;
                minValue = 1;
                break;
        }
        xAxis.setUpperBound(maxValue-1);
        xAxis.setLowerBound(minValue);
       if ((timeFrequencyData.size() == 0 && crimeData.size() == 0) ||
               (timeFrequencyData.size() != rankedTimeList(filteredCrimeData, typeOf).size() && filteredCrimeData.size() != 0) ||
               (timeFrequencyData.size() != rankedTimeList(crimeData, typeOf).size() && crimeData.size() != 0 && filteredCrimeData.size() == 0)) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            lineChart.getData().clear();
            if (filteredCrimeData.size() != 0) {
                timeFrequencyData = rankedTimeList(filteredCrimeData, typeOf);
            } else if (crimeData.size() != 0) {
                timeFrequencyData = rankedTimeList(crimeData, typeOf);
            } else {
                try {
                    timeFrequencyData = rankedTimeList(SQLiteDatabase.convertResultSet(SQLiteDatabase.selectAllFromTable(ImportController.currentTable)), typeOf);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

           timeFrequencyData.sort(Comparator.comparing(FrequencyObject::getTimePeriod));
            for (int i = minValue; i < maxValue; i++) {
                boolean found = false;
                int index = 0;
                for (FrequencyObject time : timeFrequencyData) {
                    if (time.getTimePeriod() == i) {
                        found = true;
                        index = timeFrequencyData.indexOf(time);
                    }
                }
                if (found) {
                    series.getData().add(new XYChart.Data<>(timeFrequencyData.get(index).getTimePeriod(), timeFrequencyData.get(index).getFrequency()));
                } else {
                    series.getData().add(new XYChart.Data<>(i, 0));
                }

            }
            
            series.setName("Crime Frequency Over Day");
            lineChart.getData().add(series);

        }
    }

}
