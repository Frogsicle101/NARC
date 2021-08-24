package seng202.group6.Models;

public class Time {

    protected int month;
    protected int day;
    protected int year;
    protected int hour;
    protected int minute;
    protected int second;

    public Time() {
        month = 0;
        day = 0;
        year = 0;
        hour = 0;
        minute = 0;
        second = 0;

    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }
    public int getSecond(){
        return this.second;
    }
}
