package seng202.group6.Services;

import javafx.scene.chart.XYChart;
import seng202.group6.Models.Crime;
import seng202.group6.Models.Frequency;
import seng202.group6.Models.TimeType;

import java.util.ArrayList;
import java.util.Comparator;



import static seng202.group6.Services.Rank.rankedTimeList;

public class GraphService {

    public static int maxValue = 0;
    public static int minValue = 0;


    public static XYChart.Series<Number, Number> getChartData(TimeType timeType, ArrayList<Crime> crimeData) {
        ArrayList<Frequency> timeFrequencyData;
        switch (timeType) {
            case HOUR_OF_DAY -> {
                minValue = 0;
                maxValue = 24;
            }
            case DAY_OF_WEEK -> {
                maxValue = 8;
                minValue = 1;
            }
            default -> { //MONTH_OF_YEAR
                maxValue = 13;
                minValue = 1;
            }
        }
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        timeFrequencyData = rankedTimeList(crimeData, timeType);

        timeFrequencyData.sort(Comparator.comparing(Frequency::getValue));
        for (int i = minValue; i < maxValue; i++) {
            boolean found = false;
            int index = 0;
            for (Frequency time : timeFrequencyData) {
                if (Integer.parseInt(time.getValue()) == i) {
                    found = true;
                    index = timeFrequencyData.indexOf(time);
                }
            }
            if (found) {
                series.getData().add(new XYChart.Data<>(Integer.parseInt(timeFrequencyData.get(index).getValue()), timeFrequencyData.get(index).getCount()));
            } else {
                series.getData().add(new XYChart.Data<>(i, 0));
            }

            }

        return series;
    }
}

