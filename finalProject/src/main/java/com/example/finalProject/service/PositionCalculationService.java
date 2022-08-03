package com.example.finalProject.service;


import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.utility.CalculationUtils;
import com.example.finalProject.utility.PositionCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionCalculationService implements PositionCalculator {

    private final DataFromApiService dataFromApiService;
    private final CalculationUtils calculationUtils;


    @Override
    public Double calculateTotalCost(PositionDto positionDto) {
        double totalCost = positionDto.getPurchasePrice() * positionDto.getShares();
        return calculationUtils.round(totalCost, 2);
    }

    @Override
    public Double calculateTotalEquity(PositionDto positionDto) {
        double totalEquity = dataFromApiService.getCurrentPriceFromApi(positionDto.getTicker()) * positionDto.getShares();
        return calculationUtils.round(totalEquity, 2);
    }

    @Override
    public Double calculateGainLoss(PositionDto positionDto) {
        double gainLoss = calculateTotalEquity(positionDto) - calculateTotalCost(positionDto);
        return calculationUtils.round(gainLoss, 2);
    }

    @Override
    public Double calculateGainLossPercentage(PositionDto positionDto) {
        double gainLossPercentage = (calculateGainLoss(positionDto)/calculateTotalCost(positionDto));
//        return calculationUtils.round(gainLossPercentage, 2);
        return  gainLossPercentage;
    }


}
