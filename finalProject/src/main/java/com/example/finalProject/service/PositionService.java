package com.example.finalProject.service;

import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.exception.PositionNotFoundException;
import com.example.finalProject.mapper.PositionMapper;
import com.example.finalProject.model.Position;
import com.example.finalProject.populator.PositionPopulator;
import com.example.finalProject.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionService {


    public final PositionRepository positionRepository;
    public final PositionPopulator positionPopulator;
    private final PositionMapper positionMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(PositionService.class);
    private final static String POSITION_NOT_FOUND_MSG = "POSITION WAS NOT FOUND";

    /**
     * FindAll has to return a HttpStatus OK, in other case i will see no result on angular
     *
     * @return
     */
    public ResponseEntity<List<PositionDto>> findAll() {
        LOGGER.info("FINDING ALL POSITIONS");
        List<PositionDto> allPositions = positionRepository.findAll() // Should I hide it under facade?
                .stream()
                .map(positionMapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allPositions, HttpStatus.OK);
    }

    public ResponseEntity<PositionDto> findById(Long positionId) throws PositionNotFoundException {
        LOGGER.info("FINDING POSITION BY ID");

        Position position = positionRepository
                .findById(positionId)
                .orElseThrow(() -> new PositionNotFoundException(POSITION_NOT_FOUND_MSG));
        PositionDto positionDto = positionMapper.mapToDto(position);

        return new ResponseEntity<>(positionDto, HttpStatus.OK);
    }

    public ResponseEntity<List<Position>> findByPortfolioId(Long portfolioId) {
        LOGGER.info("FINDING ALL POSITIONS BY PORTFOLIO ID");
        List<Position> byPortfolioId = positionRepository.findByPortfolioId(portfolioId);
        return new ResponseEntity<>(byPortfolioId, HttpStatus.OK);
    }

    public ResponseEntity<PositionDto> save(PositionDto positionDto) {   // Maybe I should take argument position and not dto??
        LOGGER.info("CREATING A NEW POSITION");
        Position populatedPosition = positionPopulator.populatePositionData(positionDto); // TODO should re-do this as I change the logic and return DTO in service layer
        Position newPositionEntry = positionRepository.save(populatedPosition);
        PositionDto newPositionEntryDto = positionMapper.mapToDto(newPositionEntry);
        return new ResponseEntity<>(newPositionEntryDto, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> delete(Long positionId) throws PositionNotFoundException {

        Position positionToDelete = positionRepository
                .findById(positionId)
                .orElseThrow(() -> new PositionNotFoundException(POSITION_NOT_FOUND_MSG));

        LOGGER.warn("DELETING A POSITION ENTRY");

        positionRepository.delete(positionToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<PositionDto> update(Long id, PositionDto positionDto) throws PositionNotFoundException {
        LOGGER.warn("EDITING A POSITION ENTRY");

        Position positionToEdit = positionRepository.
                findById(id).
                orElseThrow(() -> new PositionNotFoundException(POSITION_NOT_FOUND_MSG));

        positionDto.setId(positionToEdit.getId());
        Position populatedPositionEntry = positionPopulator.populatePositionData(positionDto);
        Position updatedPositionEntry = positionRepository.save(populatedPositionEntry);
        PositionDto updatedPositionEntryDto = positionMapper.mapToDto(updatedPositionEntry);

        return new ResponseEntity<>(updatedPositionEntryDto, HttpStatus.OK);
    }
}
