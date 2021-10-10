package seng202.group6.Models;

/**
 * Model for dynamic map markers which allow easily forming JSON.
 */
public class DynamicMapMarker {
    /**
     * Latitude for a marker.
     */
    private final String lat;
    /**
     * Longitude for a marker.
     */
    private final String lng;
    /**
     * Primary description of a crime
     */
    private final String crimeType;
    /**
     * Data the crime occurred
     */
    private final String date;
    /**
     * Case number of the crime
     */
    private final String crimeID;

    /**
     * Constructs a DynamicMapMarker.
     * @param lat Latitude of the Crime
     * @param lng Longitude of the Crime
     * @param crimeType Primary description of the Crime
     * @param date Date the Crime occurred
     * @param crimeID Case number of the Crime
     */
    public DynamicMapMarker(Double lat, Double lng, String crimeType, String date, String crimeID) {
        this.lat = lat.toString();
        this.lng = lng.toString();
        this.crimeType = crimeType;
        this.date = date;
        this.crimeID = crimeID;
    }

    /**
     * Formats the DynamicMapMarker into JSON format.
     * @return A String in JSON format.
     */
    @Override
    public String toString() {
        if (crimeType == null) {
            return "{lat:" + lat + ",lng:" + lng + "}";
        } else {
            return "{lat:" + lat + ",lng:" + lng + "},"+ "{crime:\"" + crimeType + "\"},{date:\""+date+"\"},{id:\""+crimeID+"\"}";
        }
    }
}
