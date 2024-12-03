package com.PropertyManager.Properties.services.impl;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
import com.PropertyManager.Properties.repositories.PersonRepository;
import com.PropertyManager.Properties.services.PersonService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public PersonEntity savePerson(PersonEntity personEntity) {


        // Save the updated person entity
        return personRepository.save(personEntity);
    }

    @Override
    public List<PersonEntity> findAll(){
        return StreamSupport
                .stream(personRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<PersonEntity> findPerson(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return personRepository.existsById(id);
    }

    @Override
    public PersonEntity partialUpdate(Long id, PersonEntity personEntity) {
        personEntity.setId(id);
        return personRepository.findById(id).map(existingPerson ->{

            Optional.ofNullable(personEntity.getFirstName()).ifPresent(existingPerson::setFirstName);
            Optional.ofNullable(personEntity.getLastName()).ifPresent(existingPerson::setLastName);
            Optional.ofNullable(personEntity.getPhoneNumber()).ifPresent(existingPerson::setPhoneNumber);
            Optional.ofNullable(personEntity.getEmail()).ifPresent(existingPerson::setEmail);

            return personRepository.save(existingPerson);
        }).orElseThrow(() -> new RuntimeException("Person does not exist"));
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}