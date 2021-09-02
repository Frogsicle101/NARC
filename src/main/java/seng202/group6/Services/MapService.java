package seng202.group6.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javafx.embed.swing.SwingFXUtils;



public class MapService {
    private static final String apiKey = "AIzaSyBZgxE6A5nvnM7aYqg49wDdK_SPKXqdLiE";

    public static LatLng geoCodeAddress(String address) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(results[0].geometry.location));

// Invoke .shutdown() after your application is done making requests
        context.shutdown();
        return results[0].geometry.location;
    }

    public static int highestLevelZoom(String address) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(results[0].addressComponents));

// Invoke .shutdown() after your application is done making requests
        context.shutdown();

        AddressComponent[] addressComponents = results[0].addressComponents;
        AddressComponentType[] addressComponentTypes = addressComponents[0].types;

        int zoomLevel = 10;
        boolean isFoundZoom = false;
        int i = 0;
        while (!isFoundZoom) {
            switch (addressComponents[i].types[0].name()) {
                case "STREET_NUMBER":
                    ;
                case "ROUTE":
                    zoomLevel = 17;
                    isFoundZoom = true;
                    break;
                case "POLITICAL":
                    for (AddressComponentType type : addressComponentTypes) {
                        if (type.name() == "SUBLOCALITY"){
                            zoomLevel = 14;
                            isFoundZoom = true;
                        }
                    }
                    break;
                case "LOCALITY":
                    zoomLevel = 10;
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
                default:
                    throw new IllegalStateException("Unexpected value: " + addressComponents[i].types[0].name());
            }
            i++;
        }

        return zoomLevel;
    }

/*
    public static Image getImage(LatLng centre) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        Size size = new Size(600, 300);

        ImageResult request = StaticMapsApi.newRequest(context, size).center(centre).zoom(10).await();
        Image image = arrayToImage(request.imageData);
        return image;
    }*/

    public static Image getStaticMap(String centre) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        Size size = new Size(700, 350);
        int zoom = highestLevelZoom(centre);
        ImageResult request = StaticMapsApi.newRequest(context, size).center(centre).zoom(zoom).maptype(StaticMapsRequest.StaticMapType.roadmap).await();
        Image image = arrayToImage(request.imageData);
        context.shutdown();
        return image;
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


}
