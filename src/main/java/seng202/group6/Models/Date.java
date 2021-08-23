package seng202.group6.Models;

public class Date extends Time{


    public Date(String date) {
        this.month = Integer.parseInt(date.substring(0, 2));
        this.day = Integer.parseInt(date.substring(3, 5));
        this.year = Integer.parseInt(date.substring(6, 10));
        this.hour = Integer.parseInt(date.substring(11, 13));
        if (date.endsWith("PM")){
            this.hour += 12;
        }

        this.minute = Integer.parseInt(date.substring(14, 16));
        this.second = Integer.parseInt(date.substring(17, 19));
    }



    public static void main(String[] args) {
        String thing = "11/23/2020 03:05:00 PM";
        Date date = new Date(thing);
        System.out.println(date.getDay());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
        System.out.println(date.getHour());
        System.out.println(date.getMinute());
        System.out.println(date.getSecond());
    }
}

