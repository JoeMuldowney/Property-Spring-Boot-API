package com.PropertyManager.Properties.services;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonEntity> findAll();

    PersonEntity savePerson(PersonEntity personEntity);

    Optional<PersonEntity> findPerson(Long id);

    boolean isExists(Long id);

    PersonEntity partialUpdate(Long id, PersonEntity personEntity);

    void deletePerson(Long id);

}
