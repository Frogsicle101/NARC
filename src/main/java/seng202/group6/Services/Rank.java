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

//Todo complete rank class to involve dangerous areas
//Todo make sure crime frequency is ranked correctly
//Todo test

public class Rank{


    /**
     * Sorts data by frequency of crime type
     * @param crimes The input to be ranked
     * @return Array list of type String in decreasing order of crime type
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
     * Sorts data by frequency of crime area
     * @param crimes The input to be ranked
     * @return Array list of type String in decreasing order of crime area
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



    public static void main(String[] args) {
        ArrayList<Crime> crimeList = new ArrayList<>();
        Crime crime1 = new Crime();
        Crime crime2 = new Crime();
        Crime crime3 = new Crime();
        crime1.setPrimaryDescription("ROBBERY");
        crime2.setPrimaryDescription("CRIMINAL DAMAGE");
        crime3.setPrimaryDescription("ROBBERY");
        crimeList.add(crime3);
        crimeList.add(crime1);
        crimeList.add(crime2);
        ArrayList<CrimeFrequency> freqList = rankedTypeList(crimeList);
        for (CrimeFrequency crime : freqList) {
            System.out.println(crime.toString());
        }
    }

}
