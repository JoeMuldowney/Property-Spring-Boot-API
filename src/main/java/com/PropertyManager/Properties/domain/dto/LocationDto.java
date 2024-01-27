package com.PropertyManager.Properties.domain.dto;

import com.PropertyManager.Properties.domain.entities.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LocationDto {

    private Long id;

    private String street;

    private String city;

    private Integer zipCode;

    private String state;

    private Double price;

    private PersonEntity person;

}
