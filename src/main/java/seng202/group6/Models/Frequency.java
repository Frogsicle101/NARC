package seng202.group6.Models;

public class Frequency {
    private String value;
    private int count;

    public Frequency(String value, int count) {
        this.value = value;
        this.count = count;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Frequency) {
            Frequency otherFrequency = (Frequency) other;
            return otherFrequency.getValue().equals(value) && otherFrequency.getCount() == count;
        } else {
            return false;
        }

    }

    public String getTime() {return value + ":00";}

    @Override
    public String toString() {
        return value + ": " + count;
    }

    public String getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

}
