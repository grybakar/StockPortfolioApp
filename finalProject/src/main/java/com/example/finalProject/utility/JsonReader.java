package com.example.finalProject.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JsonReader {

    private static String streamToString(InputStream inputStream) {
        return new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\Z").next();
    }
    public static String jsonGetRequest(String urlQueryString) {
        String jsonString = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            jsonString = streamToString(inStream); // input stream to string
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

}
