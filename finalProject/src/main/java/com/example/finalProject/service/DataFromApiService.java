package com.example.finalProject.service;

import com.example.finalProject.exception.InvalidTickerException;
import com.example.finalProject.utility.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DataFromApiService {

    private static final String EOD_LATEST_PRICE_INFO_BY_TICKER =
            "http://api.marketstack.com/v1/eod/latest?access_key=ab30072f1b21105ec724ce94832a8e89&symbols=%s";

    /**
     * getCurrentPrice methodas turetu grazinti dabartine stocko kaina.
     *
     * @param ticker - nurodome stocko sutrumpinima pagal kuri norime surasti dabartine jo kaina
     * @return - grazina double tipo kintamaji.
     * @throws JSONException
     */

    public Double getCurrentPriceFromApi(String ticker) {
        double currentPrice = 0.0;

        try {
            if (ticker == null) {
                throw new InvalidTickerException("At least one valid symbol must be provided");
            }

            String jsonGetRequest = JsonReader.jsonGetRequest(String.format(EOD_LATEST_PRICE_INFO_BY_TICKER, ticker));
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
