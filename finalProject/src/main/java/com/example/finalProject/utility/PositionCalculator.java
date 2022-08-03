package com.example.finalProject.utility;

import com.example.finalProject.dto.PositionDto;

public interface PositionCalculator {

    Double calculateTotalCost(PositionDto positionDto);

    Double calculateTotalEquity(PositionDto positionDto);

    Double calculateGainLoss(PositionDto positionDto);

    Double calculateGainLossPercentage(PositionDto positionDto);
}
