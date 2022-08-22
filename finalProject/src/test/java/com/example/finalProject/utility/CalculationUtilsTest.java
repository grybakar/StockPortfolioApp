package com.example.finalProject.utility;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertThrows;

@Slf4j
class CalculationUtilsTest {

    private static CalculationUtils calculationUtils;

    @BeforeAll
    static void executeBeforeAll() {
        calculationUtils = new CalculationUtils();
    }

    @ParameterizedTest
    @ValueSource(doubles = {140.349, 43.119, 29.06, 59.99, 61.09})
    void shouldRound(double number) {
        double round = calculationUtils.round(number, 2);
        Assertions.assertEquals(number, round, 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles = {140.349, 43.119})
    void shouldThrowIfPlacesNegativeOrZero(double number) {
        final int places = 0;
        assertThrows(IllegalArgumentException.class, () -> calculationUtils.round(number, places));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-140.349, -43.119})
    void shouldThrowIfNumberNegative(double number) {
        final int places = 2;
        assertThrows(IllegalArgumentException.class, () -> calculationUtils.round(number, places));
    }
}

