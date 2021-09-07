package seng202.group6.Models;

public class DynamicMapMarker {
    private String lat;
    private String lng;

    public DynamicMapMarker(Double lat, Double lng) {
        this.lat = lat.toString();
        this.lng = lng.toString();
    }

    public String toString() {
        return "{lat:"+lat+",lng:"+lng+"}";
    }

    /*public static void main(String[] args) {
        Double lat = 41.881832;
        Double lng = -87.623177;
        DynamicMapMarker marker = new DynamicMapMarker(lat, lng);
        System.out.println(marker);
    }*/
}
