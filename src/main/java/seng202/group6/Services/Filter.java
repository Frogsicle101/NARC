package seng202.group6.Services;

import seng202.group6.Models.Crime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Filter {

    private LocalDate start;
    private LocalDate end;
    private Set<String> types;
    private Set<String> locations;
    private boolean arrest;
    private boolean domestic;
    private Set<Integer> beats = new HashSet<>();
    private Set<Integer> wards = new HashSet<>();



    public ArrayList<Crime> applyFilter(ArrayList<Crime> crimes) {
        ArrayList<Crime> output = new ArrayList<>();
        for (Crime crime : crimes) {

            if (
                    (types.isEmpty() || types.contains(crime.getPrimaryDescription())) &&
                    (locations.isEmpty() || locations.contains(crime.getLocationDescription())) &&
                    (crime.isArrest() == arrest) &&
                    (crime.isDomestic() == domestic) &&
                    (beats.isEmpty() || beats.contains(crime.getBeat())) &&
                    (wards.isEmpty() || wards.contains(crime.getWard()))
            ) {
                output.add(crime);
            }

        }
        return output;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }

    public void setLocations(Set<String> locations) {
        this.locations = locations;
    }

    public void setArrest(boolean arrest) {
        this.arrest = arrest;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public void setBeats(String beatString) {
        if (!beatString.isEmpty()) {
            for (String beat : beatString.split(",")) {
                beats.add(Integer.parseInt(beat));
            }
        }
    }

    public void setWards(String wardString) {
        if (!wardString.isEmpty()) {
            for (String ward : wardString.split(",")) {
                wards.add(Integer.parseInt(ward));
            }
        }

    }


}





