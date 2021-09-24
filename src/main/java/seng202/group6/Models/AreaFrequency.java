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

    /**
     * The creator class for AreaFrequency taking a string representing the area as it's parameter
     * @param area The three digits of an area code
     */
    public AreaFrequency(String area) {
        this.area = area;
    }

    /**
     * Overriding the inbuilt equals object so it can be checked in lists and sorted easier
     * @param other The object this object shall be compared to
     * @return a boolean indicating if the two objects are equal, for them to be equal other must be of type AreaFrequency and have the same area code
     */

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof AreaFrequency) {
            if (((AreaFrequency) other).area.equals(this.area)) {
                equal = true;
            }
        }
        return equal;
    }

    /**
     * Returns the area string of this object
     * @return the area string consisting of the first 3 digits of the area code
     */
    public String getArea() {return this.area;}

    /**
     * Returns the frequency of crime in this area
     * @return an Integer denoting the frequency of crime in this area
     */
    public int getFrequency() {return this.frequency;}

    /**
     * Set's the area of the object to the given string
     * @param area The String to set the area of this object to
     */
    public void setArea(String area) { this.area = area;}

    /**
     * Sets the frequency of this object to the given int
     * @param frequency The integer to set the frequency of this object to
     */
    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Area: %s. Area Frequency: %d\n", this.area, this.frequency);
    }
}
