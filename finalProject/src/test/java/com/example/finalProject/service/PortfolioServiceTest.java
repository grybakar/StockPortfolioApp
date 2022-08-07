package com.example.finalProject.service;

import com.example.finalProject.model.Portfolio;
import com.example.finalProject.repository.PortfolioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    void testSave() {
        Portfolio mockPortfolio = new Portfolio(1L, "Mock", null, null);
        when(portfolioRepository.save(any())).thenReturn(mockPortfolio);
        Portfolio portfolio = portfolioRepository.save(mockPortfolio);
        assertThat(portfolio).isNotNull();
    }

//    public ResponseEntity<Portfolio> save(Portfolio portfolio) {
//        LOGGER.info("CREATING A PORTFOLIO");
//        Portfolio newPortfolio = portfolioRepository.save(portfolio);
//        return new ResponseEntity<>(newPortfolio, HttpStatus.OK);
//    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}