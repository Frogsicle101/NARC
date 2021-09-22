package seng202.group6.Models;

import java.time.LocalDateTime;

/**
 * Model for dynamic map markers which allow easily forming JSON.
 */
public class DynamicMapMarker {
    /**
     * Latitude for a marker.
     */
    private String lat;
    /**
     * Longitude for a marker.
     */
    private String lng;

    private String crimeType;

    private LocalDateTime date;

    /**
     * Constructor for a DynamicMapMarker.
     * @param lat takes a latitude as a Double.
     * @param lng takes a longitude as a Double.
     */
    public DynamicMapMarker(Double lat, Double lng) {
        this.lat = lat.toString();
        this.lng = lng.toString();
    }

    public DynamicMapMarker(Double lat, Double lng, String crimeType, LocalDateTime date) {
        this.lat = lat.toString();
        this.lng = lng.toString();
        this.crimeType = crimeType;
        this.date = date;
    }

    /**
     * Formats the lat and lng into a key value pair string.
     * @return Returns a {lat: "LAT", lng: "LNG"} key value pair, without quotes.
     */
    public String toString() {
        if (crimeType == null) {
            return "{lat:" + lat + ",lng:" + lng + "}";
        } else {
            return "{lat:" + lat + ",lng:" + lng + "},"+ "{crime:\"" + crimeType + "\"},{date:\""+date.toString()+"\"}";
        }
    }
}
