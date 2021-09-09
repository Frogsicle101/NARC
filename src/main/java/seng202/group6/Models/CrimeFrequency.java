package seng202.group6.Models;

import java.util.ArrayList;

public class CrimeFrequency {
    private String crime;
    private int frequency = 1;

    public CrimeFrequency(String crime) {
        this.crime = crime;
    }

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof CrimeFrequency) {
            if (((CrimeFrequency) other).crime == this.crime) {
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



    public static void main(String[] args) {
        CrimeFrequency crim1 = new CrimeFrequency("ABC");
        CrimeFrequency crim2 = new CrimeFrequency("ABC");
        ArrayList<CrimeFrequency> testList = new ArrayList<>();
        testList.add(crim1);
        System.out.println(testList.contains(crim2));

    }
}
