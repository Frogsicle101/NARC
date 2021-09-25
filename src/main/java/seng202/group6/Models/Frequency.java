package seng202.group6.Models;

public class Frequency {
    private String value;
    private int count;

    public Frequency(String value, int count) {
        this.value = value;
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

}
