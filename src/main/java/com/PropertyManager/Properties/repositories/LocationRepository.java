package com.PropertyManager.Properties.repositories;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {


}
