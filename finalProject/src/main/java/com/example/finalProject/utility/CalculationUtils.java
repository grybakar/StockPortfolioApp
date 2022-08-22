package com.example.finalProject.utility;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class CalculationUtils {

    public double round(double value, int places) {
        if (places <= 0 || value < 0) {
            throw new IllegalArgumentException();
        }
        return Precision.round(value, places);
    }
}
