package com.PropertyManager.Properties.repositories;


import com.PropertyManager.Properties.domain.entities.PersonEntity;
import com.PropertyManager.Properties.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonRepositoryIntegrationTests{

    private PersonRepository underTest;


    @Autowired
    public  PersonRepositoryIntegrationTests(PersonRepository underTest){
        this.underTest = underTest;
    }





    @Test
    public void testThatPersonCanBeDelete(){

        PersonEntity personA = TestDataUtil.createTestPersonA();
        underTest.save(personA);
        underTest.deleteById(personA.getId());
        Optional<PersonEntity> result = underTest.findById(personA.getId());
        assertThat(result).isEmpty();

    }





}
