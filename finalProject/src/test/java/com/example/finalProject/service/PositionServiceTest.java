package com.example.finalProject.service;

import com.example.finalProject.dto.PositionDto;
import com.example.finalProject.exception.PortfolioNotFoundException;
import com.example.finalProject.exception.PositionNotFoundException;
import com.example.finalProject.model.Portfolio;
import com.example.finalProject.model.Position;
import com.example.finalProject.populator.PositionPopulator;
import com.example.finalProject.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.*;

class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;
    @Mock
    private PositionPopulator positionPopulator;

    @InjectMocks
    private PositionService positionService;


    private static final long IDENTIFIER = 1L;


    private static final List<Position> positions = List.of(
            new Position(
                    1L,
                    "TSLA",
                    LocalDate.of(2021, 11, 12),
                    140.0,
                    1,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            ),
            new Position(
                    2L,
                    "AMZN",
                    LocalDate.of(2021, 10, 12),
                    150.0,
                    10,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            )
    );

    private static final Position position = new Position(
            IDENTIFIER,
            "TSLA",
            LocalDate.of(2021, 11, 12),
            140.0,
            1,
            null,
            null,
            null,
            null,
            null,
            new Portfolio(2L, "Mock", positions, null)
    );

    private static final PositionDto positionDto = new PositionDto(
            IDENTIFIER,
            "TSLA",
            LocalDate.of(2021, 11, 12),
            140.0,
            1,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        positionService = new PositionService(
                positionRepository,
                positionPopulator);
    }

    @Test
    void shouldFindAllPositions() {
        given(positionRepository.findAll()).willReturn(positions);
        List<Position> actual = positionService.findAll();
        assertThat(actual).isEqualTo(positions);
        verify(positionRepository).findAll();
    }

    @Test
    void shouldNotFindAllWhenEmpty() {
        List<Position> positions = List.of();
        given(positionRepository.findAll()).willReturn(positions);
        List<Position> actualPositions = positionService.findAll();
        assertThat(actualPositions).isEmpty();
    }

    @Test
    void shouldFindByPositionId() {
        when(positionRepository.findById(IDENTIFIER)).thenReturn(Optional.of(position));
        final Position actualPosition = positionService.findByPositionId(IDENTIFIER);
        assertThat(actualPosition).isEqualTo(position);
    }

    @Test
    void shouldThrowWhenUserDoesNotExist() {
        when(positionRepository.findById(IDENTIFIER)).thenReturn(Optional.empty());

        assertThatExceptionOfType(PositionNotFoundException.class)
                .isThrownBy(() -> positionService.findByPositionId(IDENTIFIER));

        verify(positionRepository).findById(IDENTIFIER);
        verifyNoInteractions(positionPopulator);
        verifyNoMoreInteractions(positionRepository);
    }

    @Test
    void shouldFindByPortfolioId() {
        List<Position> mockPositions = position.getPortfolio().getPositions();
        when(positionRepository.findByPortfolioId(IDENTIFIER))
                .thenReturn(Optional.of(mockPositions));
        List<Position> actualPositionsByPortfolioId = positionService.findByPortfolioId(IDENTIFIER);
        assertThat(actualPositionsByPortfolioId).isEqualTo(mockPositions);
        verify(positionRepository).findByPortfolioId(IDENTIFIER);
    }

    @Test
    void shouldThrowWhenPortfolioDoesNotExist() {
        when(positionRepository.findByPortfolioId(IDENTIFIER))
                .thenReturn(Optional.empty());
        assertThatExceptionOfType(PortfolioNotFoundException.class)
                .isThrownBy(() -> positionService.findByPortfolioId(IDENTIFIER));

        verify(positionRepository).findByPortfolioId(IDENTIFIER);
        verifyNoInteractions(positionPopulator);
        verifyNoMoreInteractions(positionRepository);
    }

    @Test
    void shouldSavePosition() {
        when(positionPopulator.populatePositionData(positionDto)).thenReturn(position);
        when(positionRepository.save(position)).thenReturn(position);
        Position actualPosition = positionService.save(positionDto);
        assertThat(actualPosition).isEqualTo(position);
        verify(positionPopulator).populatePositionData(positionDto);
        verify(positionRepository).save(position);
    }

    @Test
    void shouldDeletePosition() {
        when(positionRepository.findById(IDENTIFIER)).thenReturn(Optional.of(position));
        positionService.delete(IDENTIFIER);
        verify(positionRepository, times(1)).delete(position);
    }

    @Test
    @Disabled
    void update() {
        when(positionRepository.findById(IDENTIFIER)).thenReturn(Optional.of(position));
        when(positionPopulator.populatePositionData(positionDto)).thenReturn(position);
        Position actualPosition = positionService.update(IDENTIFIER, positionDto);
        assertThat(actualPosition).isEqualTo(position);
    }
}