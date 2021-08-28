package seng202.group6.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javafx.embed.swing.SwingFXUtils;



public class MapService {
    private static final String apiKey = "AIzaSyBZgxE6A5nvnM7aYqg49wDdK_SPKXqdLiE";

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

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


    public static Image getImage(LatLng centre) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        Size size = new Size(600, 300);
        ImageResult request = StaticMapsApi.newRequest(context, size).center(centre).zoom(10).await();
        Image image = arrayToImage(request.imageData);
        return image;
    }

    public static Image getImage(String centre) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        Size size = new Size(600, 300);
        ImageResult request = StaticMapsApi.newRequest(context, size).center(centre).zoom(10).await();
        Image image = arrayToImage(request.imageData);
        return image;
    }

    public static Image arrayToImage(byte[] byte_array) throws IOException {
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

    /*public static void main(String[] args) {
        LatLng coord = null;
        try {
            coord = geoCodeAddress();
            Image image = getImage(coord);
            System.out.println(image);
        } catch(Exception e) {
            System.out.println("Error " + e);
        }
    }*/
}
