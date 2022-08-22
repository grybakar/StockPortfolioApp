package com.example.finalProject.service;

import com.example.finalProject.exception.InvalidTickerException;
import com.example.finalProject.utility.ApiConnectionService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DataFromApiService {

    private final ApiConnectionService apiConnectionService;

    private static final String EOD_LATEST_PRICE_INFO_BY_TICKER =
            "http://api.marketstack.com/v1/eod/latest?access_key=972348f3ed6ddf9af5371c2673ee178f&symbols=%s";

    public Double getCurrentPriceFromApi(String ticker) {
        double currentPrice = 0.0;

        try {
            if (ticker == null) {
                throw new InvalidTickerException("At least one valid symbol must be provided");
            }

            String jsonGetRequest = apiConnectionService.connect(String.format(EOD_LATEST_PRICE_INFO_BY_TICKER, ticker));
            JSONObject apiRequestJsonObject = new JSONObject(jsonGetRequest);
            JSONArray dataJsonArray = apiRequestJsonObject.getJSONArray("data");
            JSONObject dataJsonObject = dataJsonArray.getJSONObject(0);
            currentPrice = dataJsonObject.getDouble("close");
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return currentPrice;
    }
}
