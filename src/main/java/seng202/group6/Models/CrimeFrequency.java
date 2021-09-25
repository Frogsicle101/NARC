package seng202.group6.Models;

public class CrimeFrequency {
    private String crime;
    private int frequency = 1;

    public CrimeFrequency(String crime) {
        this.crime = crime;
    }

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof CrimeFrequency) {
            if (((CrimeFrequency) other).crime.equals(this.crime)) {
                equal = true;
            }
        }
        return equal;
    }

    public String getCrime() {return this.crime;}

    public int getFrequency() {return this.frequency;}

    public void setCrime(String crime) { this.crime = crime;}

    public void setFrequency(int frequency) {this.frequency = frequency;}

    public void incrementFrequency() {this.frequency += 1;}

    @Override public String toString(){
        return String.format("Crime Type: %s. Crime Frequency: %d\n", this.crime, this.frequency);
    }

}
