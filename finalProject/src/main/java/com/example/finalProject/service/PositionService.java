package com.example.finalProject.service;

import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.exception.PortfolioNotFoundException;
import com.example.finalProject.exception.PositionNotFoundException;
import com.example.finalProject.model.Position;
import com.example.finalProject.populator.PositionPopulator;
import com.example.finalProject.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionPopulator positionPopulator;

    private final Logger LOGGER = LoggerFactory.getLogger(PositionService.class);
    private final static String POSITION_NOT_FOUND_MSG = "POSITION WAS NOT FOUND";
    private final static String PORTFOLIO_NOT_FOUND_MSG = "PORTFOLIO WAS NOT FOUND";

    public List<Position> findAll() {
        LOGGER.info("Finding all positions");
        return positionRepository.findAll();
    }

    public Position findByPositionId(Long id) {
        LOGGER.info("finding position by id: {}", id);
        return positionRepository
                .findById(id)
                .orElseThrow(() -> new PositionNotFoundException(POSITION_NOT_FOUND_MSG));
    }

    public List<Position> findByPortfolioId(Long id) {
        LOGGER.info("finding positions by portfolioId {}, :", id);
        return positionRepository
                .findByPortfolioId(id)
                .orElseThrow(() -> new PortfolioNotFoundException(PORTFOLIO_NOT_FOUND_MSG));
    }

    public Position save(PositionDto positionDto) {
        LOGGER.info("Creating a new position {}", positionDto);
        Position populatedPosition = positionPopulator.populatePositionData(positionDto);
        return positionRepository.save(populatedPosition);
    }

    public void delete(Long id) {
        LOGGER.warn("Deleting position with id: {}", id);
        Position position = positionRepository
                .findById(id)
                .orElseThrow(() -> new PositionNotFoundException(POSITION_NOT_FOUND_MSG));
        positionRepository.delete(position);
    }

    public Position update(Long id, PositionDto positionDto) {
        LOGGER.warn("Updating position with id: {}", id);
        Position positionToUpdate = positionRepository
                .findById(id)
                .map(position -> positionPopulator.populatePositionData(positionDto))
                .orElseThrow(() -> new PositionNotFoundException(POSITION_NOT_FOUND_MSG));
        positionDto.setId(positionToUpdate.getId());
        return positionRepository.save(positionToUpdate);
    }
}
