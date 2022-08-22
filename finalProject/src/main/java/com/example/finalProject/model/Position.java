package com.example.finalProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please type an appropriate stock ticker")
    private String ticker; // manualInput

    @NotNull(message = "Purchase date not selected")
    private LocalDate purchaseDate; // manualInput

    @NotNull(message = "Price can't be < 0")
    @Min(value = 0)
    private Double purchasePrice; // manualInput

    @NotNull(message = "at least 1 share is required")
    @Min(value = 1)
    private Integer shares;
    private Double totalCost;
    private Double currentPrice;
    private Double totalEquity;
    private Double gainLoss;
    private Double gainLossPercentage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "portfolioId")
    private Portfolio portfolio;
}
