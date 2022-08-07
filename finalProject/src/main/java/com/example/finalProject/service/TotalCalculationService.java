package com.example.finalProject.service;

import com.example.finalProject.dto.PositionDto;

public interface TotalCalculationService {

    Double calculateTotalCost(PositionDto positionDto);

    Double calculateTotalEquity(PositionDto positionDto);

    Double calculateGainLoss(PositionDto positionDto);

    Double calculateGainLossPercentage(PositionDto positionDto);
}
