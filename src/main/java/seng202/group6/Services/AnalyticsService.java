package seng202.group6.Services;

import seng202.group6.Models.Crime;
import java.time.LocalDateTime;

public class AnalyticsService {

    /**
     * A method used for getting the distance between two crimes in a purely geometric sense using the HaverSine Formula
     * @param otherCrime The other crime to find the distance between this one and
     * @return Returns a double representing the kilometers between the two crimes.
     */
    public static Double getDistanceBetween(Crime crime, Crime otherCrime){
        int earthRadiusKM = 6371; //Not working SICK
        double latDif = Math.abs(crime.getLatitude() - otherCrime.getLatitude()); //I think theese are busted
        double lonDif = Math.abs(crime.getLongitude() - otherCrime.getLongitude()); //I think these are busted
        double lat1 = degreesToRadians(crime.getLatitude());
        double lat2 = degreesToRadians(otherCrime.getLatitude());
        double varA = Math.sin(latDif/2) * Math.sin(latDif/2) +
                Math.sin(lonDif/2) * Math.sin(lonDif/2) * Math.cos(lat1) * Math.cos(lat2);
        double varC = 2 * Math.atan2(Math.sqrt(varA), Math.sqrt(1-varA));
        return earthRadiusKM * varC;
    }

    private static Double degreesToRadians(double degrees) {
        return (degrees * Math.PI) /180;
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
