package seng202.group6.Models;

/**
 * Stores a tuple containing the name of a field and the number of times a crime occurred in that field
 */
public class Frequency {
    private final String field;
    private final int count;

    /**
     * Constructor for a frequency object
     * @param field The name of the field that the count of crimes is linked
     * @param count The number of crime occurrences in this field
     */
    public Frequency(String field, int count) {
        this.field = field;
        this.count = count;
    }

    /**
     * An equality checker for a Frequency object and another object
     * @param other An object to be compared to
     * @return A boolean value of if the objects have the same variables
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Frequency) {
            Frequency otherFrequency = (Frequency) other;
            return otherFrequency.getField().equals(field) && otherFrequency.getCount() == count;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return field + ": " + count;
    }

    public String getField() {
        return field;
    }

    public int getCount() {
        return count;
    }

    public String getTime() {return field + ":00";}

    /**
     * Gets the day of the week corresponding to the number in value as a String
     * @return A string representation of the day of the week
     */
    public String getDay() {
        return switch (field) {
            case "1" -> "Monday";
            case "2" -> "Tuesday";
            case "3" -> "Wednesday";
            case "4" -> "Thursday";
            case "5" -> "Friday";
            case "6" -> "Saturday";
            default -> "Sunday";
        };
    }

    /**
     * Gets the month of the year corresponding to the number in value as a String
     * @return A string representation of the month of the year
     */
    public String getMonth() {
        return switch (field) {
            case "1" -> "January";
            case "2" -> "February";
            case "3" -> "March";
            case "4" -> "April";
            case "5" -> "May";
            case "6" -> "June";
            case "7" -> "July";
            case "8" -> "August";
            case "9" -> "September";
            case "10" -> "October";
            case "11" -> "November";
            default -> "December";
        };
    }
}
