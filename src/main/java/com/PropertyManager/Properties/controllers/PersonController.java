package com.PropertyManager.Properties.controllers;
import com.PropertyManager.Properties.domain.dto.PersonDto;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
import com.PropertyManager.Properties.mappers.Mapper;
import com.PropertyManager.Properties.services.PersonService;
import com.PropertyManager.Properties.services.impl.PersonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PersonController {

    private PersonService personService;
    private Mapper<PersonEntity, PersonDto> personMapper;


    public PersonController(PersonServiceImpl personService, Mapper<PersonEntity, PersonDto> personMapper ){
        this.personService = personService;
        this.personMapper = personMapper;

    }

    @PostMapping(path = "backend/person")
    public ResponseEntity<PersonDto> createPerson(
            @RequestBody PersonDto personDto){

        PersonEntity personEntity = personMapper.mapFrom(personDto);
        PersonEntity savedPersonEntity = personService.savePerson(personEntity);

        return new ResponseEntity<>(personMapper.mapTo(savedPersonEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "backend/person")
    public ResponseEntity<List<PersonDto>> listPerson(){

        List<PersonEntity> personEntities = personService.findAll();
        List<PersonDto> personDto = personEntities.stream()
                .map(personMapper::mapTo)
                .collect(Collectors.toList());

        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

    @GetMapping(path = "backend/person/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id")Long id){
     Optional<PersonEntity> foundPerson = personService.findPerson(id);
     return foundPerson.map(personEntity -> {
      PersonDto personDto = personMapper.mapTo(personEntity);
      return new ResponseEntity<>(personDto, HttpStatus.OK);

    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PutMapping(path = "backend/person/{id}")

    public ResponseEntity<PersonDto> fullUpdatePerson(
            @PathVariable("id") Long id,
            @RequestBody PersonDto personDto){
        if(!personService.isExists(id)){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        personDto.setId(id);
        PersonEntity personEntity = personMapper.mapFrom(personDto);
        PersonEntity savedPersonEntity = personService.savePerson(personEntity);
        return new ResponseEntity<>(personMapper.mapTo(savedPersonEntity), HttpStatus.OK);
    }
    @PatchMapping(path = "backend/person/{id}")
    public ResponseEntity<PersonDto> partialUpdate(
            @PathVariable("id") Long id,
        @RequestBody PersonDto personDto){
        if(!personService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PersonEntity personEntity = personMapper.mapFrom(personDto);
        PersonEntity updatedPersonEntity = personService.partialUpdate(id, personEntity);
        return new ResponseEntity<>(personMapper.mapTo(updatedPersonEntity),HttpStatus.OK);
    }
    @DeleteMapping(path = "backend/person/{id}")
    public ResponseEntity deletePerson(
            @PathVariable("id") Long id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
