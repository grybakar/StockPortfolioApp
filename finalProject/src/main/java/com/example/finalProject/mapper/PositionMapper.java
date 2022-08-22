package com.example.finalProject.mapper;

import com.example.finalProject.dto.PositionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PositionMapper {

    private final MapperConfig mapperConfig;

    public PositionDto mapToDto(com.example.finalProject.model.Position position){
        return mapperConfig.modelMapper().map(position, PositionDto.class);
    }

    public com.example.finalProject.model.Position mapToEntity(PositionDto positionDto){
        return mapperConfig.modelMapper().map(positionDto, com.example.finalProject.model.Position.class);
    }


}
