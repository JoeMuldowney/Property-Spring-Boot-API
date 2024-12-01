package com.PropertyManager.Properties.services;
import com.PropertyManager.Properties.domain.entities.LocationEntity;


import java.util.List;
import java.util.Optional;

public interface LocationService {

    LocationEntity saveLocation(LocationEntity locationEntity);


    List<LocationEntity> findAll();

    List<LocationEntity> findByPriceRange(double lower, double upper);

    Optional<LocationEntity> findOne(Long id);

    boolean isExists(Long id);


    LocationEntity fullUpdateLocation(Long id, LocationEntity locationEntity);

    LocationEntity partialUpdate(Long id, LocationEntity locationEntity);

    void deleteLocation(long id);


    LocationEntity saveNewLocation(Long personId, LocationEntity locationEntity);


}
