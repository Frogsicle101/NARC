package seng202.group6.Services;

import com.google.maps.model.LatLng;
import netscape.javascript.JSObject;

public class JavascriptMethods {

    public void callJavascript(){
        /*System.out.println("Location ");
        LatLng latLng = new LatLng();
        latLng.lat = (Double) centre.getMember("lat");
        latLng.lng = (Double) centre.getMember("lng");
        System.out.println("calling javasctript: "+ latLng.lat);
        System.out.println("calling javasctript: "+ latLng.lng);

        DynamicMapService.loadSearchMarkers(latLng);
        System.out.println("dont like you");*/
        DynamicMapService.removeMarkers();
        DynamicMapService.removeLocationMarker();
        DynamicMapService.addLocationMarker();
        DynamicMapService.loadSearchMarkers();
    }
}
