package com.example.finalProject.utility;

import com.example.finalProject.exception.NoValidSymbolProvidedException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
@Slf4j
public class ApiConnectionService {

    public String connect(String urlQueryString) {
        String jsonString = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                log.error(String.valueOf(connection.getResponseCode()));
                log.error(connection.getResponseMessage());
                InputStream errorStream = connection.getErrorStream();
                log.error(streamToString(errorStream));
                throw new NoValidSymbolProvidedException("At least one valid symbol must be provided");
            }

            InputStream inStream = connection.getInputStream();
            jsonString = streamToString(inStream); // input stream to string
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    private String streamToString(InputStream inputStream) {
        return new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\Z").next();
    }

}
