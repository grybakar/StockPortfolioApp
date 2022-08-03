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
    private Integer shares; // manualInput

    private Double totalCost; //BUSINESS LOGIC REQUIRED:  purchase_price * shares
    private Double currentPrice; // //BUSINESS LOGIC REQUIRED: NEED TO GET THIS FROM AN EXTERNAL API
    private Double totalEquity; //BUSINESS LOGIC REQUIRED: currentPrice * shares
    private Double gainLoss;  //BUSINESS LOGIC REQUIRED: total_equity - totalCost
    private Double gainLossPercentage; // //BUSINESS LOGIC REQUIRED: total_equity - totalCost / 100%

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "portfolioId")
    private Portfolio portfolio;
}
