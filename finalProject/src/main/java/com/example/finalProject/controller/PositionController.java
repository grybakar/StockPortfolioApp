package com.example.finalProject.controller;

import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.exception.PositionNotFoundException;
import com.example.finalProject.model.Position;
import com.example.finalProject.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class PositionController {

    /**
     * 2022.07.25
     * Controller layer has to return a DTO to a client.
     */

    private final PositionService positionService;


    @GetMapping(path = "/positions")
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        return positionService.findAll();
    }

    @GetMapping(path = "/positions/{positionId}")
    public ResponseEntity<PositionDto> getPositionById(@PathVariable final Long positionId) throws PositionNotFoundException {
        return positionService.findById(positionId);
    }

    @GetMapping(path = "/portfolio/{portfolioId}")
    public ResponseEntity<List<Position>> getPositionByPortfolioId(@PathVariable final Long portfolioId) {
        return positionService.findByPortfolioId(portfolioId);
    }

    @PostMapping(path = "/positions")
    public ResponseEntity<PositionDto> postPosition(@Valid @RequestBody final PositionDto positionDto) {
        return positionService.save(positionDto);
    }

    @DeleteMapping(path = "/positions/{positionId}")
    public ResponseEntity<HttpStatus> deletePosition(@PathVariable final Long positionId) throws PositionNotFoundException {
        return positionService.delete(positionId);
    }

    @PutMapping(path = "/positions/edit/{positionId}")
    public ResponseEntity<PositionDto> updatePosition(@PathVariable final Long positionId,
                                                      @Valid @RequestBody final PositionDto positionDto) throws PositionNotFoundException {
        return positionService.update(positionId, positionDto);
    }

}
