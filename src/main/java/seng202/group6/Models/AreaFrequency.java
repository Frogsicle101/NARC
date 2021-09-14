package seng202.group6.Models;

import java.util.ArrayList;

public class AreaFrequency {
    private String area;
    private int frequency = 1;

    public AreaFrequency(String area) {
        this.area = area;
    }

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof AreaFrequency) {
            if (((AreaFrequency) other).area == this.area) {
                equal = true;
            }
        }
        return equal;
    }

    public String getCrime() {return this.area;}

    public int getFrequency() {return this.frequency;}

    public void setCrime(String area) { this.area = area;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Area: %s. Area Frequency: %d\n", this.area, this.frequency);
    }
}
