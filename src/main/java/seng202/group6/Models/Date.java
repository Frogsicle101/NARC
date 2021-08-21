package seng202.group6.Models;

public class Date {
    private int month;
    private int day;
    private int year;
    private int hour;
    private int minute;

    public Date(String date) {
        this.month = Integer.parseInt(date.substring(0, 2));
        this.day = Integer.parseInt(date.substring(3, 5));
        this.year = Integer.parseInt(date.substring(6, 10));
        this.hour = Integer.parseInt(date.substring(11, 13));
        if (date.endsWith("PM")){
            this.hour += 12;
        }

        this.minute = Integer.parseInt(date.substring(14, 16));
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

    public static void main(String[] args) {
        String thing = "11/23/2020 03:05:00 PM";
        Date date = new Date(thing);
        System.out.println(date.getDay());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
        System.out.println(date.getHour());
        System.out.println(date.getMinute());
    }
}

