package com.example.finalProject.controller;

import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.model.Position;
import com.example.finalProject.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping(path = "/positions")
    public ResponseEntity<List<Position>> findPositions() {
        return ResponseEntity.ok().body(positionService.findAll());
    }

    @GetMapping(path = "/positions/{positionId}")
    public ResponseEntity<Position> findPositionById(@PathVariable final Long positionId) {
        Position position = positionService.findByPositionId(positionId);
        return ResponseEntity.ok().body(position);
    }

    @GetMapping(path = "/portfolio/{portfolioId}/positions")
    public ResponseEntity<List<Position>> getPositionByPortfolioId(@PathVariable final Long portfolioId) {
        List<Position> positions = positionService.findByPortfolioId(portfolioId);
        return ResponseEntity.ok().body(positions);
    }

    @PostMapping(path = "/positions")
    public ResponseEntity<Position> savePosition(@Valid @RequestBody final PositionDto positionDto) {
        return new ResponseEntity<>(positionService.save(positionDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/positions/{positionId}")
    public ResponseEntity<HttpStatus> deletePosition(@PathVariable final Long positionId) {
        positionService.delete(positionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/positions/edit/{positionId}")
    public ResponseEntity<Position> updatePosition(@PathVariable final Long positionId,
                                                   @Valid @RequestBody final PositionDto positionDto) {
        Position updatePosition = positionService.update(positionId, positionDto);
        return new ResponseEntity<>(updatePosition, HttpStatus.OK);
    }
}
