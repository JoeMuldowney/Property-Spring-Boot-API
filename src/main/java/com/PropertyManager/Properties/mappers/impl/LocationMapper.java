package com.PropertyManager.Properties.mappers.impl;
import com.PropertyManager.Properties.domain.dto.LocationDto;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import com.PropertyManager.Properties.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper implements Mapper<LocationEntity, LocationDto> {


    private ModelMapper modelMapper;

    public LocationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LocationDto mapTo(LocationEntity location) {
        return modelMapper.map(location, LocationDto.class);
    }

    @Override
    public LocationEntity mapFrom(LocationDto locationDto) {
        return modelMapper.map(locationDto, LocationEntity.class);
    }

}
