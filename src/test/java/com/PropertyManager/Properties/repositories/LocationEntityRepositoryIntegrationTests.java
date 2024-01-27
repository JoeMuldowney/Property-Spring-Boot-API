package com.PropertyManager.Properties.repositories;




import com.PropertyManager.Properties.domain.entities.LocationEntity;
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
public class LocationEntityRepositoryIntegrationTests {

    private PersonRepository underTest2;
    private LocationRepository underTest;


    @Autowired
    public LocationEntityRepositoryIntegrationTests(LocationRepository underTest, PersonRepository underTest2) {
        this.underTest = underTest;
        this.underTest2 = underTest2;
    }


        @Test
        public void testThatLocationCanBeUpdated(){
            PersonEntity personA = TestDataUtil.createTestPersonA();
            underTest2.save(personA);

            LocationEntity locationEntityA = TestDataUtil.createTestLocationA(personA);

            underTest.save(locationEntityA);

            locationEntityA.setState("Delaware");
            underTest.save(locationEntityA);

            Optional<LocationEntity> result = underTest.findById(locationEntityA.getId());
            assertThat(result).isPresent();
            assertThat(result.get().getCity()).isEqualTo("Brandon");

    }

    @Test
    public void testThatLocationCanBeDeleted(){

        PersonEntity person = TestDataUtil.createTestPersonA();
        underTest2.save(person);
        LocationEntity locationEntityA = TestDataUtil.createTestLocationA(person);
        underTest.save(locationEntityA);
        underTest.deleteById(locationEntityA.getId());
        Optional<LocationEntity> result = underTest.findById(locationEntityA.getId());
        assertThat(result).isEmpty();


    }





}
