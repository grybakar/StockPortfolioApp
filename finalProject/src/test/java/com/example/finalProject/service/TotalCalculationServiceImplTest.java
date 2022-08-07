package com.example.finalProject.service;

import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.utility.CalculationUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class TotalCalculationServiceImplTest {

    @Spy
    private CalculationUtils calculationUtils;

    @Spy
    private DataFromApiService dataFromApiService;

    @InjectMocks
    private TotalCalculationServiceImpl totalCalculationServiceImpl;

    @Test
    void calculateTotalCost() {
        PositionDto positionDto = new PositionDto(1L, "AAPL", LocalDate.of(2012, 10, 12), 150.53, 10, 0.0, 0.0, 0.0, 0.0, 0.0, null, null);
        double totalCost = positionDto.getPurchasePrice() * positionDto.getShares();
        double round = calculationUtils.round(totalCost, 2);

        assertThat(totalCost).isEqualTo(1505.3);
        assertThat(round).isEqualTo(1505.3);

    }

//    @Override
//    public Double calculateTotalCost(PositionDto positionDto) {
//        double totalCost = positionDto.getPurchasePrice() * positionDto.getShares();
//        return calculationUtils.round(totalCost, 2);
//    }


    @Test
    void calculateTotalEquity() {
    }

    @Test
    void calculateGainLoss() {
    }

    @Test
    void calculateGainLossPercentage() {
    }
}