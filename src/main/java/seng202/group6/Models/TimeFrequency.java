package seng202.group6.Models;

public class TimeFrequency {
    private int hourOfTheDay;
    private int frequency = 1;
    private String hourString;

    public TimeFrequency(int hourOfTheDay) {
        this.hourOfTheDay = hourOfTheDay;
    }

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof TimeFrequency) {
            if (((TimeFrequency) other).hourOfTheDay == this.hourOfTheDay) {
                equal = true;
            }
        }
        return equal;
    }

    public int getHourOfTheDay() {return this.hourOfTheDay;}

    public int getFrequency() {return this.frequency;}

    public String getHourString() { return this.hourOfTheDay + ":00";}

    public void setHourOfTheDay(int hourOfTheDay) { this.hourOfTheDay = hourOfTheDay;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Hour of the day: %d. Area Frequency: %d\n", this.hourOfTheDay, this.frequency);
    }
}
