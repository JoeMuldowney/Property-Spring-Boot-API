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
        // Retrieve the existing person entity from the database
        PersonEntity existingEntity = personRepository.findById(personEntity.getId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        // Update only the fields of the person entity
        existingEntity.setFirstName(personEntity.getFirstName());
        existingEntity.setLastName(personEntity.getLastName());
        existingEntity.setEmail(personEntity.getEmail());
        existingEntity.setPhoneNumber(personEntity.getPhoneNumber());

        // Save the updated person entity
        return personRepository.save(existingEntity);
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
