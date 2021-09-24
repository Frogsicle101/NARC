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

    private ArrayList<FrequencyObject> timeFrequencyData = new ArrayList<FrequencyObject>();

    private int typeOf = 0; //0 for HourOfDay, 1 for DayOfWeek, 2 for MonthOfYear

    private boolean flag = false;

    private ArrayList<CrimeFrequency> data = new ArrayList<>();

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
               (timeFrequencyData.size() != rankedTimeList(crimeData, typeOf).size() && crimeData.size() != 0)) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            lineChart.getData().clear();

            if (crimeData.size() != 0) {
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

            lineChart.getData().add(series);

        }
    }

    public void clickDay() throws IOException {
        pieChart.setVisible(false);
        typeOf = 0;
        xAxis.setLabel("Time of Day");
        clickApplyChart();
        int i;
        for (i = 0; i < 24; i++) {
            //todo
            xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, ":00"));
        }
        lineChart.setVisible(true);

    }

    public void clickWeek() throws IOException {
        pieChart.setVisible(false);
        typeOf = 1;
        xAxis.setLabel("Time of Week");
        clickApplyChart();
        //String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int i;
        for (i = 0; i < 7; i++) {
            //todo
            xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, null));
        }
        lineChart.setVisible(true);
    }

    public void clickYear() throws IOException {
        pieChart.setVisible(false);
        typeOf = 2;
        xAxis.setLabel("Time of Year");
        clickApplyChart();
        int i;
        for (i = 0; i < 12; i++) {
            //todo
            xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, null));
        }
        lineChart.setVisible(true);
    }

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
