package com.PropertyManager.Properties.repositories;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {


}
