package com.example.finalProject.mapper;

import com.example.finalProject.dto.PortfolioDto;
import com.example.finalProject.model.Portfolio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PortfolioMapper {

    private final MapperConfig mapperConfig;

    public PortfolioDto mapToDto(Portfolio portfolio) {
        return mapperConfig.modelMapper().map(portfolio, PortfolioDto.class);
    }

    public Portfolio mapToEntity(PortfolioDto portfolioDto) {
        return mapperConfig.modelMapper().map(portfolioDto, Portfolio.class);
    }


}
