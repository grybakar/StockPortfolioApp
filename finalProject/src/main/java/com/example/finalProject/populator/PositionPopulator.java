package com.example.finalProject.populator;

import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.model.Portfolio;
import com.example.finalProject.model.Position;
import com.example.finalProject.service.DataFromApiService;
import com.example.finalProject.service.PositionCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionPopulator {

    private final PositionCalculationService positionCalculationService;
    private final DataFromApiService dataFromApiService;

    /**
     * populatePositionData is used to set information to a data object.
     * @param positionDto data transfer object of position entity
     * @return position
     */

    public Position populatePositionData(PositionDto positionDto) {
        Position position = new Position(); // creating an entity
        position.setId(positionDto.getId());
        position.setTicker(positionDto.getTicker());
        position.setPurchaseDate(positionDto.getPurchaseDate());
        position.setPurchasePrice(positionDto.getPurchasePrice());
        position.setShares(positionDto.getShares());
        position.setTotalCost(positionCalculationService.calculateTotalCost(positionDto));
        position.setTotalEquity(positionCalculationService.calculateTotalEquity(positionDto));
        position.setGainLoss(positionCalculationService.calculateGainLoss(positionDto));
        position.setGainLossPercentage(positionCalculationService.calculateGainLossPercentage(positionDto));
        position.setCurrentPrice(dataFromApiService.getCurrentPriceFromApi(positionDto.getTicker()));
        position.setPortfolio(Portfolio
                .builder()
                .id(positionDto.getPortfolioId()).build());
        return position;
    }
}
