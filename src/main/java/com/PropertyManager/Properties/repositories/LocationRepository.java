package com.PropertyManager.Properties.repositories;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {

    List<LocationEntity> findByPriceBetween(double lower, double upper);
    List<LocationEntity> findByState(String state);


}
