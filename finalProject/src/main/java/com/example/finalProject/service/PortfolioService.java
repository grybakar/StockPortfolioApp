package com.example.finalProject.service;

import com.example.finalProject.dto.PortfolioDto;
import com.example.finalProject.exception.PortfolioNotFoundException;
import com.example.finalProject.mapper.PortfolioMapper;
import com.example.finalProject.model.Portfolio;
import com.example.finalProject.model.Position;
import com.example.finalProject.populator.PortfolioPopulator;
import com.example.finalProject.repository.PortfolioRepository;
import com.example.finalProject.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.sound.sampled.Port;
import javax.swing.text.html.HTML;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PortfolioService {

    private final Logger LOGGER = LoggerFactory.getLogger(PortfolioService.class);
    private final static String PORTFOLIO_NOT_FOUND_MSG = "Portfolio with id: %s was not found";

    public final PortfolioRepository portfolioRepository;
    public final PortfolioMapper portfolioMapper;
    public final PortfolioPopulator portfolioPopulator;


//    public ResponseEntity<PortfolioDto> save(PortfolioDto portfolioDto) {
//        LOGGER.info("CREATING A PORTFOLIO");
//        Portfolio portfolio = portfolioPopulator.populatePortfolioData(portfolioDto);
//        Portfolio newPortfolio = portfolioRepository.save(portfolio);
//        PortfolioDto newPortfolioDto = portfolioMapper.mapToDto(newPortfolio);
//        return new ResponseEntity<>(newPortfolioDto, HttpStatus.OK);
//    }

    public ResponseEntity<Portfolio> save(Portfolio portfolio) {
        LOGGER.info("CREATING A PORTFOLIO");
        Portfolio newPortfolio = portfolioRepository.save(portfolio);
        return new ResponseEntity<>(newPortfolio, HttpStatus.OK);
    }

    public ResponseEntity<List<Portfolio>> findAll() {
        LOGGER.info("FINDING ALL PORTFOLIOS");
        List<Portfolio> portfolios = portfolioRepository.findAll();
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    public ResponseEntity<Portfolio> findById(Long id) {
        LOGGER.info("FINDING PORTFOLIO BY ID");
        Portfolio portfolio = portfolioRepository
                .findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException(PORTFOLIO_NOT_FOUND_MSG));
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }


    public ResponseEntity<HttpStatus> delete(Long id) {
        Portfolio portfolioToDelete = portfolioRepository.findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException(String.format(PORTFOLIO_NOT_FOUND_MSG, id)));
        portfolioRepository.delete(portfolioToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Portfolio> update(Long id, Portfolio portfolio) {
        Portfolio portfolioToUpdate = portfolioRepository.findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException(String.format(PORTFOLIO_NOT_FOUND_MSG, id)));
        portfolioToUpdate.setName(portfolio.getName());
        portfolioRepository.save(portfolioToUpdate);
        return new ResponseEntity<>(portfolioToUpdate, HttpStatus.OK);
    }


}
