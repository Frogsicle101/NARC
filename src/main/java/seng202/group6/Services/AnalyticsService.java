package seng202.group6.Services;

import seng202.group6.Models.Crime;

import java.time.LocalDateTime;

public class AnalyticsService {

    /**
     * A method used for getting the distance between two crimes in a purely geometric sense (i.e. not accounting for buildings and
     * the likes in the way)
     * @param otherCrime The other crime to find the distance between this one and
     * @return Returns a double representing the kilometers between the two crimes.
     */
    public static Double getDistanceBetween(Crime crime, Crime otherCrime){
        Double x = (crime.getLongitude() - otherCrime.getLongitude()) * 111; //Don't ask me what is going on I don't know either
        Double y = (crime.getLatitude() - otherCrime.getLatitude()) * 111;  //But it works. The *111 is to convert to KM's
        return Math.hypot(x, y); //Again return value is in km
    }


    /**
     * This method compares two LocalDateTimes of crimes, if the value returned from the comparison is negative then the
     * crime from which this method is called is smaller and vice versa if positive
     * @param otherCrime Another object of type crime of which the dates shall be compared
     * @return An object of LocalDateTime which represents the difference in time between the crime, at
     * the moment it returns an object with months
     *
     */
    public static LocalDateTime compareCrimeTimes(Crime crime,Crime otherCrime) {
        LocalDateTime dateTime;
        if (crime.getDate().compareTo(otherCrime.getDate()) < 0) {
            dateTime = getTimeDifference(crime.getDate(), otherCrime.getDate());
        } else {
            dateTime = getTimeDifference(otherCrime.getDate(), crime.getDate());
        }
        return dateTime;
    }

    /**
     * This calculates the time difference between two LocalDateTimes using the internal methods to return a LocalDateTime object
     * representing the temporial difference between the two dates
     * @param smallTime The smaller of the two LocalDateTimes
     * @param bigTime The bigger of the two LocalDateTimes
     * @return Returns a LocalDateTime object representing the difference in time between the two LocalDateTimes
     */
    private static LocalDateTime getTimeDifference(LocalDateTime smallTime, LocalDateTime bigTime) {
        //TODO Optimize later
        LocalDateTime dateTime = bigTime;
        dateTime = dateTime.minusSeconds(smallTime.getSecond());
        dateTime = dateTime.minusMinutes(smallTime.getMinute());
        dateTime = dateTime.minusHours(smallTime.getHour());
        dateTime = dateTime.minusDays(smallTime.getDayOfMonth());
        dateTime = dateTime.minusMonths(smallTime.getMonthValue());
        dateTime = dateTime.minusYears(smallTime.getYear());
        return dateTime;

    }
}
