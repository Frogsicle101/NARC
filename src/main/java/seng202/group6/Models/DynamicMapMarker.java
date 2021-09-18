package seng202.group6.Models;

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

    /**
     * Constructor for a DynamicMapMarker.
     * @param lat takes a latitude as a Double.
     * @param lng takes a longitude as a Double.
     */
    public DynamicMapMarker(Double lat, Double lng) {
        this.lat = lat.toString();
        this.lng = lng.toString();
    }

    /**
     * Formats the lat and lng into a key value pair string.
     * @return Returns a {lat: "LAT", lng: "LNG"} key value pair, without quotes.
     */
    public String toString() {
        return "{lat:"+lat+",lng:"+lng+"}";
    }
}
