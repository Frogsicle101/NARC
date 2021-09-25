package seng202.group6.Models;

public class TimeFrequency {
    private int timePeriod;
    private int frequency = 1;


    public TimeFrequency(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public int getTimePeriod() {return this.timePeriod;}

    public int getFrequency() {return this.frequency;}

    public String getTimeString() { return this.timePeriod + ":00";}

    public void setTimePeriod(int timePeriod) { this.timePeriod = timePeriod;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Time Period: %d. Frequency: %d\n", this.timePeriod, this.frequency);
    }
}
