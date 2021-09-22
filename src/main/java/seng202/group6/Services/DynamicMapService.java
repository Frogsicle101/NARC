package seng202.group6.Services;

import com.google.maps.model.LatLng;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import seng202.group6.Controllers.MasterController;
import seng202.group6.Models.Crime;
import seng202.group6.Models.DynamicMapMarker;

import java.io.File;
import java.sql.ResultSet;
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
        window.setMember("app", javascript);
        /*
        webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                window.setMember("app", javascript);
            }
        });*/
        /*webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                System.out.println("Done3");
            }
        });*/
        File file = new File("src/main/resources/HTML/EmbedMaps.html");
        try {
            webEngine.load(file.toURI().toString());
        } catch (Exception e) {
            System.out.println("Error in initializeDynamicMap: " + e);
        }


    }

    public static WebView getMapView() {
        return mapView;
    }

    public static String addDynamicMapMarkers(ArrayList<Crime> crimes) {
        String script = "addMarkers([";
        if (!crimes.isEmpty()) {
            for (int i = 0; i < 50; i++) {
                DynamicMapMarker marker = new DynamicMapMarker(crimes.get(i).getLatitude(), crimes.get(i).getLongitude());
                script += marker.toString();
                script += ",";
            }
        }
        script += "])";

        return script;
    }


    public static void loadMarkers(ArrayList<Crime> crimes) {
        ArrayList<Crime> crimes1 = new ArrayList<Crime>();
        /* Sorry we broke your test crimes, the constructor changed
        crimes1.add(new Crime("1", "11/23/2020 03:05:00 PM", "3", "4", "5", "6", "11", "Y", "Y", 10, 9, "12", "41.85", "-87.65"));
        crimes1.add(new Crime("1", "11/23/2020 03:05:00 PM", "3", "4", "5", "6", "11", "Y", "Y", 10, 9, "12", "41.85", "-86.65"));
        crimes1.add(new Crime("1", "11/23/2020 03:05:00 PM", "3", "4", "5", "6", "11", "Y", "Y", 10, 9, "12", "42.85", "-87.65"));
        */
        //System.out.println(crimes.size());
        /*for (int i = 0; i < crimes.size(); i++) {
            System.out.println(crimes.get(i));
        }*/
        String script = addDynamicMapMarkers(crimes);
        mapView.getEngine().executeScript(script);
    }

    public static void loadMarker(ArrayList<Crime> crimes) {
        for (int i = 0; i < crimes.size(); i++) {
            String script = "addMarker([";

            DynamicMapMarker marker = new DynamicMapMarker(crimes.get(i).getLatitude(), crimes.get(i).getLongitude(),
                    crimes.get(i).getPrimaryDescription(), crimes.get(i).getDate());
            script += marker.toString();
            script += ",])";
            mapView.getEngine().executeScript(script);
        }

    }

    public static void loadMarkerLocation(ArrayList<LatLng> locations) {
        for (int i = 0; i < locations.size(); i++) {
            String script = "addMarker([";

            DynamicMapMarker marker = new DynamicMapMarker(locations.get(i).lat, locations.get(i).lng);
            script += marker.toString();
            script += ",])";
            mapView.getEngine().executeScript(script);
        }

    }

    public static LatLng getCentre() {
        JSObject location = (JSObject) mapView.getEngine().executeScript("getLocation()");
        Double lat = (Double) location.getMember("lat");
        Double lng = (Double) location.getMember("lng");
        LatLng centre = new LatLng();
        centre.lat = lat;
        centre.lng = lng;
        //System.out.println("lat: " + centre.lat);
        //System.out.println("lng: "+ centre.lng);
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
                /*ArrayList<Crime> locations = new ArrayList<Crime>();
                ResultSet resultSet = SQLiteDatabase.selectLocationsFromTable(getCentre());
                ArrayList<Crime> crimes = SQLiteDatabase.convertResultSet(resultSet);*/
                Filter newFilter = new Filter();
                newFilter.setCentre(getCentre());
                ArrayList<Crime> crimes = newFilter.applyFilter();
                loadMarker(crimes);
            } catch (Exception e) {
                System.out.println("Error in DynamicMapService.loadSearchMarkers: " + e);
                e.printStackTrace();
            }
        }
    }

    public static void loadFilteredMarkers(Filter filter) throws SQLException {
        filter.setCentre(getCentre());
        ArrayList<Crime> crimes = filter.applyFilter();
        loadMarker(crimes);
    }

    public static void addLocationMarker() {
        DynamicMapService.getMapView().getEngine().executeScript("addLocationMarker()");
    }

    public static void main(String[] args) {
        try {
            SQLiteDatabase.connectToDatabase();
        } catch (Exception e) {
            System.out.println("Error connecting to db: " + e);
        }
        LatLng centre = getCentre();
        System.out.println(centre.lat);
        System.out.println(centre.lng);
    }


}