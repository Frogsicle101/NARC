package seng202.group6.DynamicMapSRC;

import com.google.maps.model.LatLng;
import netscape.javascript.JSObject;
import seng202.group6.Controllers.DataController;
import seng202.group6.Controllers.ImportController;
import seng202.group6.Controllers.MasterController;
import seng202.group6.Models.Crime;
import seng202.group6.Services.DynamicMapService;
import seng202.group6.Services.SQLiteDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class JavascriptMethods {

    public void addMarkersToMap(){
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

    public void viewInfo(String crimeID) {
        String sql = "SELECT * FROM " + ImportController.currentTable + " WHERE case_id = '" + crimeID +"';";
        try {
            ArrayList<Crime> crimes = SQLiteDatabase.convertResultSet(SQLiteDatabase.executeQuery(sql));
            MasterController.launchViewScreen(crimes.get(0));
        } catch (SQLException | IOException e) {
            System.out.println("SQL Exception in JavascriptMethods.viewInfo: " + e);
            e.printStackTrace();
        }

    }
}
