package com.example.finalProject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class PortfolioDto {

    private Long id;

    @NotBlank(message = "Please type an appropriate portfolio name")
    private String name;
    private List<PositionDto> positionDto;
    private ClientDto clientDto;
}
