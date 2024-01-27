package com.PropertyManager.Properties;

import com.PropertyManager.Properties.domain.dto.LocationDto;
import com.PropertyManager.Properties.domain.dto.PersonDto;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import com.PropertyManager.Properties.domain.entities.PersonEntity;

public final class TestDataUtil {

    private TestDataUtil(){}
    public static PersonEntity createTestPersonA() {
        return PersonEntity.builder()
                .id(1L)
                .firstName("Joe")
                .lastName("Muldowney")
                .build();
    }
    public static PersonEntity createTestPersonB() {
        return PersonEntity.builder()
                .id(2L)
                .firstName("Joseph Henry")
                .lastName("Muldowney")
                .build();
    }

    public static PersonDto createTestPersonC(){

        return PersonDto.builder()
                .id(3L)
                .firstName("Joseph Henry")
                .lastName("Muldowney")
                .build();
    }

    public static LocationEntity createTestLocationA(final PersonEntity person) {
        return LocationEntity.builder()
                .id(1L)
                .street("1612 Harvard Woods Dr")
                .city("Brandon")
                .zipCode(33511)
                .state("Florida")
                .price(350000.0)
                .person(person)
                .build();
    }
    public static LocationEntity createTestLocationB(final PersonEntity person) {
        return LocationEntity.builder()
                .id(2L)
                .street("12 Doko Market Street")
                .city("Brandon")
                .zipCode(26512)
                .state("Florida")
                .price(250000.0)
                .person(person)
                .build();
    }
    public static LocationEntity createTestLocationC(final PersonEntity person) {
        return LocationEntity.builder()
                .id(3L)
                .street("16 Flamingo Drive")
                .city("Brandon")
                .zipCode(33511)
                .state("Florida")
                .price(5000000.0)
                .person(person)
                .build();
    }

    public static LocationDto createTestLocationDtoA() {
        return LocationDto.builder()
                .id(1L)
                .street("236 Flamebart Drive")
                .zipCode(33511)
                .city("Brandon")
                .state("Florida")
                .price(5000000.0)
                .build();
    }
}
