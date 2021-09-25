package seng202.group6.Services;

import seng202.group6.Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Function;

/**
 * class used for ranking of crimes in terms of Area, Type of crime and Time
 */


public class Rank{



    private static ArrayList<Frequency> rankedList(ArrayList<Crime> crimes, Function<Crime, String> function) {
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (Crime crime : crimes) {
            String key = function.apply(crime);
            frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
        }

        ArrayList<Frequency> data = new ArrayList<>();
        for (String key : frequencies.keySet()) {
            data.add(new Frequency(key, frequencies.get(key)));
        }

        data.sort(Comparator.comparing(Frequency::getCount));
        Collections.reverse(data);
        return data;
    }

    /**
     * Using the CrimeFrequency class this method sorts an array of crimes into an ordered list of Crime Frequencies and returns it
     * @param crimes An array list of Crime objects
     * @return A sorted array list of CrimeFrequencies
     */
    public static ArrayList<Frequency> rankedTypeList(ArrayList<Crime> crimes) {
        return rankedList(crimes, Crime::getPrimaryDescription);

    }



    /**
     * Using the AreaFrequency class this method sorts an array of crimes into an ordered list of AreaFrequency objects and returns it
     * @param crimes An array list of Crime objects
     * @return A sorted array list of AreaFrequency
     */
    public static ArrayList<Frequency> rankedAreaList(ArrayList<Crime> crimes) {
        return rankedList(crimes, crime -> crime.getBlock().substring(0, 3));
    }

    /**
     * Using the HourOfDayFrequency class this method sorts an array of crimes into an ordered list of HourOfDayFrequency objects and returns it
     * @param crimes An array list of Crime objects
     * @return A sorted array list of HourOfDayFrequency
     */
    public static ArrayList<Frequency> rankedTimeList(ArrayList<Crime> crimes, TimeType timeType) {

        ArrayList<Frequency> out;

        switch (timeType) {
            case MONTH_OF_YEAR ->
                out = rankedList(crimes, crime -> String.valueOf(crime.getDate().getMonthValue()));
            case DAY_OF_WEEK ->
                out = rankedList(crimes, crime -> String.valueOf(crime.getDate().getDayOfWeek().getValue()));
            default -> //HOUR_OF_DAY
                out = rankedList(crimes, crime -> String.valueOf(crime.getDate().getHour()));
        }
        return out;
    }



   

}
