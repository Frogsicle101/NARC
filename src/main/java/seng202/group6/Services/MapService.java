package seng202.group6.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.google.maps.StaticMapsRequest.Markers;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javafx.embed.swing.SwingFXUtils;
import seng202.group6.Models.Crime;


public class MapService {
    private static final String apiKey = "AIzaSyBZgxE6A5nvnM7aYqg49wDdK_SPKXqdLiE";

    public static GeocodingResult[] geoCodeAddress(GeoApiContext context, String address) throws IOException, InterruptedException, ApiException {

        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address).await();
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //System.out.println(gson.toJson(results[0].geometry.location));
        return results;
    }

    public static int highestLevelZoom(GeocodingResult[] results) throws IOException, InterruptedException, ApiException {

        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(results[0]));

        AddressComponent[] addressComponents = results[0].addressComponents;
        AddressComponentType[] addressComponentTypes = addressComponents[0].types;

        int zoomLevel = 10;
        boolean isFoundZoom = false;
        int i = 0;
        while (!isFoundZoom && i < addressComponents.length) {
            switch (addressComponents[i].types[0].name()) {
                case "STREET_NUMBER":
                    ;
                case "ROUTE":
                    zoomLevel = 16;
                    isFoundZoom = true;
                    break;
                case "NEIGHBORHOOD":
                    zoomLevel = 14;
                    isFoundZoom = true;
                case "POLITICAL":
                    for (AddressComponentType type : addressComponentTypes) {
                        if (type.name() == "SUBLOCALITY"){
                            zoomLevel = 14;
                            isFoundZoom = true;
                        }
                    }
                    break;
                case "LOCALITY":
                    zoomLevel = 11;
                    isFoundZoom = true;
                    break;
                case "ADMINISTRATIVE_AREA_LEVEL_1":
                    zoomLevel = 8;
                    isFoundZoom = true;
                    break;
                case "COUNTRY":
                    zoomLevel = 5;
                    isFoundZoom = true;
                    break;
            }
            i++;
        }
        if (isFoundZoom != true) {
            zoomLevel = 2;
        }

        return zoomLevel;
    }


    public static Image getStaticMap(String centre, ArrayList<Crime> crimeData) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        GeocodingResult[] geocodingResult = geoCodeAddress(context, centre);
        LatLng centreLatLng = geocodingResult[0].geometry.location;

        Size size = new Size(700, 350);
        StaticMapsRequest request = StaticMapsApi.newRequest(context, size);
        request.center(centreLatLng).zoom(highestLevelZoom(geocodingResult)).maptype(StaticMapsRequest.StaticMapType.roadmap);
        Markers markers = addMapMarkers(request, crimeData, centreLatLng);
        request.markers(markers);
        ImageResult result = request.await();
        Image image = arrayToImage(result.imageData);
        context.shutdown();
        return image;
    }

    public static Markers addMapMarkers(StaticMapsRequest request, ArrayList<Crime> crimeData, LatLng centreLocation) {
        Markers markers = new Markers();
        int numMarkers = 0;
        for (int i =0; i < crimeData.size(); i++) {
            try {
                Double crimeLat = Double.parseDouble(crimeData.get(i).getLatitude());
                Double crimeLng = Double.parseDouble(crimeData.get(i).getLongitude());
                LatLng crimeLocation = new LatLng(crimeLat, crimeLng);
                Double distanceToCentre = getDistanceFromCentre(crimeData.get(i), centreLocation);
                if ((distanceToCentre < 6) && (numMarkers < 500)) {
                    //System.out.println("Lat: " + crimeLat + " Long: " + crimeLng + " Distance: " + distanceToCentre);
                    markers.addLocation(crimeLocation);
                    numMarkers++;
                }
            } catch (java.lang.NumberFormatException e) {
                //System.out.println("empty crime: " + crimeData.get(i).getCaseNumber());
            }
        }
        //System.out.println("number of markers: " +numMarkers);
        return markers;
    }

    public static Image arrayToImage(byte[] byte_array) {
        Image image = null;
        try {
            ByteArrayInputStream input_stream = new ByteArrayInputStream(byte_array);
            BufferedImage final_buffered_image = ImageIO.read(input_stream);
            image = SwingFXUtils.toFXImage(final_buffered_image, null);

        } catch (Exception e) {
            System.out.println(e);
        }

        return image;
    }

    public static Double getDistanceFromCentre(Crime otherCrime, LatLng centre){
        Double x = (centre.lng - Double.parseDouble(otherCrime.getLongitude())) * 111; //Don't ask me what is going on I don't know either
        Double y = (centre.lat - Double.parseDouble(otherCrime.getLatitude())) * 111;  //But it works. The *111 is to convert to KM's
        return Math.hypot(x, y); //Again return value is in km
    }


    /*public static void main(String[] args) {
        ArrayList<Crime> crimeData = null;
        String address = "Chicago";
        try {
            Image image = MapService.getStaticMap(address, crimeData);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }*/


}
