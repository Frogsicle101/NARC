package seng202.group6.Services;

import seng202.group6.Controllers.MasterController;
import seng202.group6.Models.AreaFrequency;
import seng202.group6.Models.Crime;
import seng202.group6.Models.CrimeFrequency;
import seng202.group6.Models.TimeFrequency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

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
     * Using the TimeFrequency class this method sorts an array of crimes into an ordered list of TimeFrequency objects and returns it
     * @param crimes An array list of Crime objects
     * @return A sorted array list of TimeFrequency
     */
    public static ArrayList<TimeFrequency> rankedTimeList(ArrayList<Crime> crimes) {
        ArrayList<TimeFrequency> data = new ArrayList<TimeFrequency>();
        boolean found;
        for (Crime crime : crimes) {
            found = false;
            TimeFrequency timeFrequency = new TimeFrequency(crime.getDate().getHour());
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getHourOfTheDay() == (timeFrequency.getHourOfTheDay())) {
                    data.get(i).incrementFrequency();
                    found = true;
                    break;
                }
            }
            if (!found) {
                data.add(timeFrequency);
            }
        }
        data.sort(Comparator.comparing(TimeFrequency::getFrequency));
        Collections.reverse(data);
        return data;
    }

}
