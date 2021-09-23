package seng202.group6.Models;

public class FrequencyObject {
    private int timePeriod;
    private int frequency = 1;


    public FrequencyObject(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof FrequencyObject) {
            if (((FrequencyObject) other).timePeriod == this.timePeriod) {
                equal = true;
            }
        }
        return equal;
    }

    public int getTimePeriod() {return this.timePeriod;}

    public int getFrequency() {return this.frequency;}


    public void setTimePeriod(int timePeriod) { this.timePeriod = timePeriod;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Time Period: %d. Area Frequency: %d\n", this.timePeriod, this.frequency);
    }
}
