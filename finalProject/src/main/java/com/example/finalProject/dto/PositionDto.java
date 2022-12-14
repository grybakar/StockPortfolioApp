package com.example.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private Long id;

    @NotBlank(message = "Please type an appropriate stock ticker")
    private String ticker;

    @NotNull(message = "Purchase date not selected")
    private LocalDate purchaseDate;

    @NotNull(message = "Price can't be < 0")
    @Min(value = 0)
    private Double purchasePrice;

    @NotNull(message = "at least 1 share is required")
    @Min(value = 1)
    private Integer shares;

    private Double totalCost;
    private Double currentPrice;
    private Double totalEquity;
    private Double gainLoss;
    private Double gainLossPercentage;
    private PortfolioDto portfolioDto;
    private Long portfolioId;
}
