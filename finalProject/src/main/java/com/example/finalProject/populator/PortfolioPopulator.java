package com.example.finalProject.populator;

import com.example.finalProject.dto.PortfolioDto;
import com.example.finalProject.model.Portfolio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PortfolioPopulator {

    public Portfolio populatePortfolioData(PortfolioDto portfolioDto) {

        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioDto.getId());
        portfolio.setName(portfolioDto.getName());
        return portfolio;
    }


}
