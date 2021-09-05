package seng202.group6.Models;

import java.util.Calendar;
@Deprecated
public class Date extends Time{

    private Calendar calendar = new Calendar() {
        //Calendar is abstract so i have to implement theese methods.
        @Override
        protected void computeTime() {

        }

        @Override
        protected void computeFields() {

        }

        @Override
        public void add(int field, int amount) {

        }

        @Override
        public void roll(int field, boolean up) {

        }

        @Override
        public int getMinimum(int field) {
            return 0;
        }

        @Override
        public int getMaximum(int field) {
            return 0;
        }

        @Override
        public int getGreatestMinimum(int field) {
            return 0;
        }

        @Override
        public int getLeastMaximum(int field) {
            return 0;
        }
    };


    public Date(String date) {
        //Potentially should throw error if string in not right format, i.e. if 61 seconds in a minute or negative numbers
        //TODO write exception/errors for this method
        this.month = Integer.parseInt(date.substring(0, 2));
        this.day = Integer.parseInt(date.substring(3, 5));
        this.year = Integer.parseInt(date.substring(6, 10));
        this.hour = Integer.parseInt(date.substring(11, 13));
        if (date.endsWith("PM")){
            this.hour += 12;
        }

        this.minute = Integer.parseInt(date.substring(14, 16));
        this.second = Integer.parseInt(date.substring(17, 19));
        this.calendar.set(this.year, this.month, this.day, this.hour, this.minute, this.second);
    }

    public Time compare(Date date){
        //older calendar (earlier one) needs to be passed as the argument
        int milliseconds = 0;
        if (this.calendar.compareTo(date.calendar) > 0) {
            milliseconds = this.calendar.compareTo(date.calendar);
        } else {
            milliseconds = date.calendar.compareTo(this.calendar);
        }
        return this.convertToTime(milliseconds);
    }

    /*
    @Override
    public String toString() {
        return String.format("%d")
    }
    */





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

