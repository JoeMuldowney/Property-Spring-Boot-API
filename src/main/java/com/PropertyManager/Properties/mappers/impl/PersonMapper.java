package com.PropertyManager.Properties.mappers.impl;
import com.PropertyManager.Properties.domain.dto.PersonDto;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
import com.PropertyManager.Properties.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements Mapper<PersonEntity, PersonDto> {

    private ModelMapper modelMapper;


    public PersonMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public PersonDto mapTo(PersonEntity personEntity) {
       return modelMapper.map(personEntity, PersonDto.class);
    }

    @Override
    public PersonEntity mapFrom(PersonDto personDto){
        return modelMapper.map(personDto, PersonEntity.class);
    }



}
