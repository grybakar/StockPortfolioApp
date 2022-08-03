package com.example.finalProject.controller;

import com.example.finalProject.dto.PortfolioDto;
import com.example.finalProject.model.Portfolio;
import com.example.finalProject.repository.PortfolioRepository;
import com.example.finalProject.service.PortfolioService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping(path = "/portfolios")
    public ResponseEntity<List<Portfolio>> getPortfolios() {
        return portfolioService.findAll();
    }

    @GetMapping(path = "/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> findById(@PathVariable final Long portfolioId) {
        return portfolioService.findById(portfolioId);
    }

    @PostMapping(path = "/portfolios")
    public ResponseEntity<Portfolio> savePortfolio(@Valid @RequestBody final Portfolio portfolio) {
        return portfolioService.save(portfolio);
    }

    @DeleteMapping(path = "/portfolios/{portfolioId}")
    public ResponseEntity<HttpStatus> deletePortfolio(@PathVariable final Long portfolioId) {
        return portfolioService.delete(portfolioId);
    }

    @PutMapping(path = "/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable final Long portfolioId,
                                                     @RequestBody Portfolio portfolio) {
        return portfolioService.update(portfolioId, portfolio);
    }

}
