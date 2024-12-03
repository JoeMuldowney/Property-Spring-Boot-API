package com.PropertyManager.Properties.services.impl;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
import com.PropertyManager.Properties.repositories.LocationRepository;
import com.PropertyManager.Properties.repositories.PersonRepository;
import com.PropertyManager.Properties.services.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    private PersonRepository personRepository;


    public LocationServiceImpl(LocationRepository locationRepository, PersonRepository personRepository) {
        this.locationRepository = locationRepository;
        this.personRepository = personRepository;
    }

    @Override
    public LocationEntity saveLocation(LocationEntity locationEntity) {


        return locationRepository.save(locationEntity);
    }

    @Override
    public LocationEntity saveNewLocation(Long personId, LocationEntity locationEntity) {
        Optional<PersonEntity> existingPerson = personRepository.findById(personId);
        if(existingPerson.isPresent())
        {
            PersonEntity personEntity = existingPerson.get();
            locationEntity.setPerson(personEntity);
            return locationRepository.save(locationEntity);
        }else {
           throw new RuntimeException("Person Not Found");
        }

    }

    @Override
    public List<LocationEntity> findAll() {
        return StreamSupport
                .stream(locationRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LocationEntity> findOne(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return locationRepository.existsById(id);
    }

    @Override
    public LocationEntity fullUpdateLocation(Long id, LocationEntity locationEntity) {
        locationEntity.setId(id);
        return locationRepository.save(locationEntity);
    }

    @Override
    public LocationEntity partialUpdate(Long id, LocationEntity locationEntity) {
        locationEntity.setId(id);
        return locationRepository.findById(id).map(existingLocation ->{
            Optional.ofNullable(locationEntity.getStreet()).ifPresent(existingLocation::setStreet);
            Optional.ofNullable(locationEntity.getState()).ifPresent(existingLocation::setState);
            Optional.ofNullable(locationEntity.getCity()).ifPresent(existingLocation::setCity);
            Optional.ofNullable(locationEntity.getZipCode()).ifPresent(existingLocation::setZipCode);
            Optional.ofNullable(locationEntity.getPrice()).ifPresent(existingLocation::setPrice);
            return locationRepository.save(existingLocation);
        }).orElseThrow(() -> new RuntimeException("Location does not exist"));

    }

    @Override
    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public List<LocationEntity> findByPriceRange(double lower, double upper) {
        return locationRepository.findByPriceBetween(lower, upper);
    }

    @Override
    public List<LocationEntity> findAllByState(String state) {
        return locationRepository.findByState(state);
    }



}
