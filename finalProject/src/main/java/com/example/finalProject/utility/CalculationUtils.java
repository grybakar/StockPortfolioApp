package com.example.finalProject.utility;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculationUtils {

    //calculation util klase labiau describitve
    /**
     * Created a separate mathclass, with a method, which rounds up double values, with a chosen decimal places.
     *
     * @param value  value that you choose to round,
     * @param places decimal places you want to see after comma
     * @return - a round up value.
     */
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP); // setscale(choose decimal place, half_up - meaning round 0.5 == 1
        return bd.doubleValue();
    }
}
