package seng202.group6.Services;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import seng202.group6.Controllers.ImportController;
import seng202.group6.Models.Crime;
import seng202.group6.Models.TimeFrequency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;



import static seng202.group6.Services.Rank.rankedTimeList;

public class GraphService {

    public static int maxValue = 0;
    public static int minValue = 0;


    public static XYChart.Series<Number, Number> getChartData(int typeOf,  ArrayList<Crime> crimeData) {
        ArrayList<TimeFrequency> timeFrequencyData;
        XYChart.Series<Number, Number> series = null;
        switch (typeOf) {
            case 0:
                minValue = 0;
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
        series = new XYChart.Series<>();
        timeFrequencyData = rankedTimeList(crimeData, typeOf);

        timeFrequencyData.sort(Comparator.comparing(TimeFrequency::getTimePeriod));
        for (int i = minValue; i < maxValue; i++) {
            boolean found = false;
            int index = 0;
            for (TimeFrequency time : timeFrequencyData) {
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

        return series;
    }
}

