package seng202.group6.Models;

import java.util.Calendar;

public class Time {

    protected int month;
    protected int day;
    protected int year;
    protected int hour;
    protected int minute;
    protected int second;
    private int week; //Private as date has no need for week


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

    public void setWeek(int week) { this.week = week;}

    public int getWeek() {return this.week;}

    public void setMonth(int month) { this.month = month;}

    public void setDay(int day) {this.day = day;}

    public void setYear(int year) {this.year = year;}

    public void setHour(int hour) {this.hour = hour;}

    public void setMinute(int minute) {this.minute = minute;}

    public void setSecond(int second) {this.second = second;}


    protected Time convertToTime(int miliseconds){
        //Need to test
        Time time = new Time();
        //Converting miliseconds into years
        while (miliseconds >= 3.15*(10^10)){
            miliseconds -= 3.15*(10^10);
            time.setYear(time.getYear() + 1);
        }
        //Converting miliseconds into months
        while (miliseconds >= 2.628*(10^9)){
            miliseconds -= 2.628*(10^9);
            time.setMonth(time.getMonth() + 1);
        }
        //Converting miliseconds to weeks
        while (miliseconds >= 6.048*(10^8)){
            miliseconds -= 6.048*(10^8);
            time.setWeek(time.getWeek() + 1);
        }
        //Converting miliseconds to days
        while (miliseconds >= 8.64e+7){
            miliseconds -= 8.64e+7;
            time.setDay(time.getDay() + 1);
        }
        //Converting miliseconds to hours
        while (miliseconds >= 3.6e+6){
            miliseconds -= 3.6e+6;
            time.setHour(time.getHour() + 1);
        }
        //Converting miliseconds to minutes
        while (miliseconds >= 60000){
            miliseconds -= 60000;
            time.setMinute(time.getMinute() + 1);
        }
        //Converting miliseconds to seconds, after that the remainder doesn't matter
        while (miliseconds >= 1000){
            miliseconds -= 1000;
            time.setSecond(time.getSecond() + 1);
        }

        return time;

    }
}
