package seng202.group6.Models;
@Deprecated
public class DayOfWeekFrequency {
    private int DayOfWeek;
    private int frequency = 1;


    public DayOfWeekFrequency(int DayOfWeek) {
        this.DayOfWeek = DayOfWeek;
    }

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof DayOfWeekFrequency) {
            if (((DayOfWeekFrequency) other).DayOfWeek == this.DayOfWeek) {
                equal = true;
            }
        }
        return equal;
    }

    public int getDayOfWeek() {return this.DayOfWeek;}

    public int getFrequency() {return this.frequency;}


    public void setDayOfWeek(int DayOfWeek) { this.DayOfWeek = DayOfWeek;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Day of Week: %d. Area Frequency: %d\n", this.DayOfWeek, this.frequency);
    }
}
