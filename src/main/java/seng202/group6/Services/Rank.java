package seng202.group6.Services;

import seng202.group6.Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * class used for ranking of crimes in terms of Area, Type of crime and Time
 */

public class Rank{


    /**
     * Using the CrimeFrequency class this method sorts an array of crimes into an ordered list of Crime Frequencies and returns it
     * @param crimes An array list of Crime objects
     * @return A sorted array list of CrimeFrequencies
     */
    public static ArrayList<CrimeFrequency> rankedTypeList(ArrayList<Crime> crimes) {
        ArrayList<CrimeFrequency> data = new ArrayList<CrimeFrequency>();
        boolean found;
        for (Crime crime : crimes) {
            found = false;
            CrimeFrequency crimeFrequency = new CrimeFrequency(crime.getPrimaryDescription());
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getCrime().equals(crimeFrequency.getCrime())) {
                    data.get(i).incrementFrequency();
                    found = true;
                    break;
                }
            }
            if (!found) {
                data.add(crimeFrequency);
            }
        }
        data.sort(Comparator.comparing(CrimeFrequency::getFrequency));
        Collections.reverse(data);
        return data;
    }



    /**
     * Using the AreaFrequency class this method sorts an array of crimes into an ordered list of AreaFrequency objects and returns it
     * @param crimes An array list of Crime objects
     * @return A sorted array list of AreaFrequency
     */
    public static ArrayList<AreaFrequency> rankedAreaList(ArrayList<Crime> crimes) {
        ArrayList<AreaFrequency> data = new ArrayList<AreaFrequency>();
        boolean found;
        for (Crime crime : crimes) {
            found = false;
            AreaFrequency areaFrequency = new AreaFrequency(crime.getBlock().substring(0, 3));
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getArea().equals(areaFrequency.getArea())) {
                    data.get(i).incrementFrequency();
                    found = true;
                    break;
                }
            }
            if (!found) {
                data.add(areaFrequency);
            }
        }
        data.sort(Comparator.comparing(AreaFrequency::getFrequency));
        Collections.reverse(data);
        return data;
    }

    /**
     * Using the HourOfDayFrequency class this method sorts an array of crimes into an ordered list of HourOfDayFrequency objects and returns it
     * @param crimes An array list of Crime objects
     * @return A sorted array list of HourOfDayFrequency
     */
    public static ArrayList<FrequencyObject> rankedTimeList(ArrayList<Crime> crimes, int typeOf) {
        ArrayList<FrequencyObject> data = new ArrayList<FrequencyObject>();
        boolean found;
        for (Crime crime : crimes) {
            found = false;
            FrequencyObject frequencyObject = null;
            switch (typeOf) {
                case 0: frequencyObject = new FrequencyObject(crime.getDate().getHour());
                    break;
                case 1 : frequencyObject = new FrequencyObject(crime.getDate().getDayOfWeek().getValue());
                    break;
                case 2: frequencyObject = new FrequencyObject(crime.getDate().getMonthValue());
                    break;    
            }
            //new FrequencyObject(crime.getDate().getHour());
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getTimePeriod() == (frequencyObject.getTimePeriod())) {
                    data.get(i).incrementFrequency();
                    found = true;
                    break;
                }
            }
            if (!found) {
                data.add(frequencyObject);
            }
        }
        data.sort(Comparator.comparing(FrequencyObject::getFrequency));
        Collections.reverse(data);
        return data;
    }



   

}
