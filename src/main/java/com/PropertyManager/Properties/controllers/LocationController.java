package com.PropertyManager.Properties.controllers;

import com.PropertyManager.Properties.domain.dto.LocationDto;

import com.PropertyManager.Properties.domain.entities.LocationEntity;

import com.PropertyManager.Properties.mappers.Mapper;
import com.PropertyManager.Properties.services.LocationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class LocationController {

    private final Mapper<LocationEntity, LocationDto> locationMapper;


    private final LocationService locationService;




    public LocationController(Mapper<LocationEntity, LocationDto> locationMapper, LocationService locationService){
        this.locationMapper = locationMapper;
        this.locationService = locationService;


    }


    @PutMapping("/location/{id}")
    public ResponseEntity<LocationDto> fullUpdateLocation(
            @PathVariable("id") Long id,
            @RequestBody LocationDto locationDto) {

        boolean locationExists = locationService.isExists(id);


        LocationEntity locationEntity = locationMapper.mapFrom(locationDto);
        LocationEntity savedLocationEntity = locationService.fullUpdateLocation(id, locationEntity);
        LocationDto savedUpdatedLocationDto = locationMapper.mapTo(savedLocationEntity);

        if (locationExists) {
            return new ResponseEntity<>(savedUpdatedLocationDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedUpdatedLocationDto, HttpStatus.CREATED);
        }
    }
    @PostMapping("/location/{id}")
    public ResponseEntity<LocationDto> addLocation(
            @PathVariable("id") Long personId,
            @RequestBody LocationDto locationDto){

        LocationEntity locationEntity = locationMapper.mapFrom(locationDto);
        LocationEntity savedLocationEntity = locationService.saveNewLocation(personId, locationEntity);
        LocationDto savedUpdatedLocationDto = locationMapper.mapTo(savedLocationEntity);

        return new ResponseEntity<>(savedUpdatedLocationDto, HttpStatus.CREATED);

    }

    @GetMapping(path = "/location")
    public List<LocationDto> listLocation(){
        List<LocationEntity> location = locationService.findAll();
        return location.stream().map(locationMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/location/{id}")
    public ResponseEntity<LocationDto> getLocation(@PathVariable("id") Long id){

        Optional<LocationEntity> foundLocation = locationService.findOne(id);
        return foundLocation.map(locationEntity -> {
            LocationDto locationDto =  locationMapper.mapTo(locationEntity);
            return new ResponseEntity<>(locationDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/location/{id}")
    public ResponseEntity<LocationDto> partialUpdateLocation (
            @PathVariable("id")Long id,
            @RequestBody LocationDto locationDto){

        if(!locationService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocationEntity locationEntity = locationMapper.mapFrom(locationDto);
        LocationEntity updatedLocationEntity = locationService.partialUpdate(id, locationEntity);
        return new ResponseEntity<>(locationMapper.mapTo(updatedLocationEntity),HttpStatus.OK);

    }

    @DeleteMapping(path = "/location/{id}")
    public ResponseEntity deleteLocation(@PathVariable("id")long id){
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/report")
//    public ResponseEntity<List<LocationDto>> getLocationsByPriceRange(
//            @RequestParam double lower,
//            @RequestParam double upper) {
//
//        List<LocationEntity> locationEntities = locationService.findByPriceRange(lower, upper);
//        List<LocationDto> locationDtos = locationEntities.stream()
//                .map(entity -> new LocationDto(entity.getId(), entity.getName(), entity.getPrice()))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(locationDtos, HttpStatus.OK);
//    }
}