package seng202.group6.Models;

/**
 *  A class used to represent the frequency of crimes in an area
 */
public class AreaFrequency {

    /**
     * The first 3 digits of an area code
     */
    private String area;

    /**
     * The frequency of crimes in that area
     */
    private int frequency = 1;

    public AreaFrequency(String area) {
        this.area = area;
    }

    public String getArea() {return this.area;}

    public int getFrequency() {return this.frequency;}

    public void setArea(String area) { this.area = area;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Area: %s. Area Frequency: %d\n", this.area, this.frequency);
    }
}
