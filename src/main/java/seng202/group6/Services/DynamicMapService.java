package seng202.group6.Services;

import javafx.event.ActionEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seng202.group6.Models.Crime;
import seng202.group6.Models.DynamicMapMarker;

import java.io.File;
import java.util.ArrayList;

public class DynamicMapService {
    private static WebView mapView;

    public static void initializeDynamicMap() {
        mapView = new WebView();
        WebEngine webEngine = mapView.getEngine();
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
        crimes1.add(new Crime("1", "11/23/2020 03:05:00 PM", "3", "4", "5", "6", true, true, 9, 10, "11", "12", 41.85, -87.65));
        crimes1.add(new Crime("1", "11/23/2020 03:05:00 PM", "3", "4", "5", "6", true, true, 9, 10, "11", "12", 41.85, -86.65));
        crimes1.add(new Crime("1", "11/23/2020 03:05:00 PM", "3", "4", "5", "6", true, true, 9, 10, "11", "12", 42.85, -87.65));
        //System.out.println(crimes.size());
        /*for (int i = 0; i < crimes.size(); i++) {
            System.out.println(crimes.get(i));
        }*/
        String script = addDynamicMapMarkers(crimes);
        mapView.getEngine().executeScript(script);
    }

    public static void removeMarkers() {
        mapView.getEngine().executeScript("removeMarkers()");
    }


}