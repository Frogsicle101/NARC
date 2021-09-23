package seng202.group6.Models;
@Deprecated
public class MonthFrequency {
    private int MonthOfYear;
    private int frequency = 1;


    public MonthFrequency(int MonthOfYear) {
        this.MonthOfYear = MonthOfYear;
    }

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof MonthFrequency) {
            if (((MonthFrequency) other).MonthOfYear == this.MonthOfYear) {
                equal = true;
            }
        }
        return equal;
    }

    public int getMonthOfYear() {return this.MonthOfYear;}

    public int getFrequency() {return this.frequency;}
    

    public void setMonthOfYear(int MonthOfYear) { this.MonthOfYear = MonthOfYear;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Month of the Year: %d. Area Frequency: %d\n", this.MonthOfYear, this.frequency);
    }
}
