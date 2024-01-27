package com.PropertyManager.Properties.controllers;

import com.PropertyManager.Properties.TestDataUtil;
import com.PropertyManager.Properties.domain.dto.LocationDto;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
import com.PropertyManager.Properties.services.LocationService;
import com.PropertyManager.Properties.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class LocationControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private LocationService locationService;
    private PersonService personService;

    @Autowired
    public LocationControllerIntegrationTests(MockMvc mockMvc, LocationService locationService, ObjectMapper objectMapper, PersonService personService) {
        this.mockMvc = mockMvc;
        this.locationService = locationService;
        this.objectMapper = objectMapper;
        this.personService = personService;
    }


    @Test
    public void testThatCreateLocationReturnsCreatedLocation() throws Exception {

        LocationDto locationDto = TestDataUtil.createTestLocationDtoA();
        String createLocationJson = objectMapper.writeValueAsString(locationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/location/" + locationDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createLocationJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(locationDto.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.street").value(locationDto.getStreet())
        );


    }

    @Test
    public void testThatGetLocationReturnsHttpStatus200WhenLocationExists() throws Exception {

        LocationEntity locationEntity = TestDataUtil.createTestLocationB(null);
        locationService.saveLocation(locationEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/location/"+locationEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void testThatGetLocationReturnsHttpStatus204WhenLocationDoesNotExist() throws Exception {

        LocationEntity locationEntity = TestDataUtil.createTestLocationB(null);
        locationService.saveLocation(locationEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/location/99")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatListLocationReturnsLocations() throws Exception {

        LocationEntity locationEntity = TestDataUtil.createTestLocationB(null);
        locationService.saveLocation(locationEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/location")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].street").value("12 Doko Market Street")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].state").value("Florida")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].price").value(250000.0)
        );


    }


    @Test
    public void testThatUpdateLocationReturnsUpdatedLocation() throws Exception {
        LocationEntity locationEntity1 = TestDataUtil.createTestLocationA(null);
        LocationEntity savedLocationEntity1 = locationService.fullUpdateLocation(locationEntity1.getId(), locationEntity1);

        LocationEntity locationEntity2 = TestDataUtil.createTestLocationB(null);
        LocationEntity savedLocationEntity2 = locationService.fullUpdateLocation(locationEntity2.getId(), locationEntity2);


        LocationDto locationDto = TestDataUtil.createTestLocationDtoA();
        locationDto.setId(savedLocationEntity2.getId());
        locationDto.setCity("UPDATED");
        String updateLocationJson = objectMapper.writeValueAsString(locationDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/location/" + locationEntity2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateLocationJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(2L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.street").value("236 Flamebart Drive")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.city").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.zipCode").value(33511)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.state").value("Florida")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.price").value(5000000.0)
        );


    }
    @Test
    public void testThatPartialUpdateLocationReturnsHttpStatus200() throws Exception {

        LocationEntity locationEntityA = TestDataUtil.createTestLocationA(null);
        locationService.saveLocation(locationEntityA);

        LocationDto locationDto = TestDataUtil.createTestLocationDtoA();
        locationDto.setPrice(555555.0);

        String updateLocationJson = objectMapper.writeValueAsString(locationDto);


        mockMvc.perform(
                MockMvcRequestBuilders.patch("/location/" + locationEntityA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateLocationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateLocationReturnsUpdatedLocation() throws Exception {

        LocationEntity locationEntityA = TestDataUtil.createTestLocationA(null);
        locationService.saveLocation(locationEntityA);

        LocationDto locationDto = TestDataUtil.createTestLocationDtoA();
        locationDto.setPrice(555555.0);

        String updateLocationJson = objectMapper.writeValueAsString(locationDto);


        mockMvc.perform(
                MockMvcRequestBuilders.patch("/location/" + locationEntityA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateLocationJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.street").value("1612 Harvard Woods Dr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.city").value("Brandon")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.zipCode").value(33511)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.state").value("Florida")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.price").value(555555.0)
        );

    }

    @Test
    public void testThatDeleteLocationReturnsHttpStatus204ForNonExistingLocation() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/location/2356")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());


    }
    @Test
    public void testThatDeleteLocationReturnsHttpStatus204ForExistingLocation() throws Exception{



        LocationEntity locationEntity = TestDataUtil.createTestLocationB(null);
        LocationEntity savedLocation = locationService.saveLocation(locationEntity);

        String createLocationJson = objectMapper.writeValueAsString(savedLocation);

        //String updateLocationJson = objectMapper.writeValueAsString(locationDto);



        mockMvc.perform(
                MockMvcRequestBuilders.delete("/person/"+savedLocation.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());


    }

}
