package seng202.group6.Services;

import com.google.maps.model.LatLng;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import seng202.group6.Controllers.MasterController;
import seng202.group6.DynamicMapSRC.JavascriptMethods;
import seng202.group6.Models.Crime;
import seng202.group6.Models.DynamicMapMarker;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service for creating and manipulating a dynamic Google Map
 * which is loaded by a javafx.scene.web.WebEngine Object.
 */
public class DynamicMapService {
    private static WebView mapView;
    private static JSObject window;
    private static JavascriptMethods javascript = new JavascriptMethods();

    public static void initializeDynamicMap() {
        mapView = new WebView();
        WebEngine webEngine = mapView.getEngine();
        window = (JSObject) mapView.getEngine().executeScript("window");
        window.setMember("javascriptMethods", javascript);
        File file = new File("src/main/java/seng202/group6/DynamicMapSRC/EmbedMaps.html");
        try {
            webEngine.load(file.toURI().toString());
        } catch (Exception e) {
            System.out.println("Error in initializeDynamicMap: " + e);
        }
    }

    public static WebView getMapView() {
        return mapView;
    }

    public static void loadMarker(ArrayList<Crime> crimes) {
        for (int i = 0; i < crimes.size(); i++) {
            String script = "addMarker([";

            DynamicMapMarker marker = new DynamicMapMarker(crimes.get(i).getLatitude(), crimes.get(i).getLongitude(),
                    crimes.get(i).getPrimaryDescription(), crimes.get(i).getReadableDate(), crimes.get(i).getCaseNumber());
            script += marker.toString();
            script += ",])";
            mapView.getEngine().executeScript(script);
        }
    }

    public static LatLng getMapCentre() {
        JSObject location = (JSObject) mapView.getEngine().executeScript("getLocation()");
        Double lat = (Double) location.getMember("lat");
        Double lng = (Double) location.getMember("lng");
        LatLng centre = new LatLng();
        centre.lat = lat;
        centre.lng = lng;
        return centre;
    }

    public static void removeMarkers() {
        mapView.getEngine().executeScript("removeMarkers()");
    }

    public static void removeLocationMarker() {
        DynamicMapService.getMapView().getEngine().executeScript("removeLocationMarker()");
    }

    public static void loadSearchMarkers() {
        Filter filter = MasterController.getFilter();
        if (filter != null) {
            try {
                loadFilteredMarkers(filter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Filter newFilter = new Filter();
                newFilter.setCentre(getMapCentre());
                ArrayList<Crime> crimes = newFilter.applyFilter();
                loadMarker(crimes);
            } catch (Exception e) {
                System.out.println("Error in DynamicMapService.loadSearchMarkers: " + e);
                e.printStackTrace();
            }
        }
    }

    public static void loadFilteredMarkers(Filter filter) throws SQLException {
        filter.setCentre(getMapCentre());
        ArrayList<Crime> crimes = filter.applyFilter();
        loadMarker(crimes);
    }

    public static void addLocationMarker() {
        DynamicMapService.getMapView().getEngine().executeScript("addLocationMarker()");
    }
}