package com.PropertyManager.Properties.controllers;

import com.PropertyManager.Properties.TestDataUtil;
import com.PropertyManager.Properties.domain.dto.PersonDto;
import com.PropertyManager.Properties.domain.entities.PersonEntity;
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
public class PersonControllerIntegrationTests {

    private MockMvc mockMvc;

    private PersonService personService;

    private ObjectMapper objectMapper;

    @Autowired
    public PersonControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, PersonService personService) {
        this.mockMvc = mockMvc;
        this.personService = personService;
        this.objectMapper = new ObjectMapper();

    }





    @Test
    public void testThatListPersonReturnsHttpStatus200() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/person")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());


    }



    @Test
    public void testThatFullUpdatePersonSuccessfullyReturnsHttpStatus404WhenNoPersonExists() throws Exception {
        PersonDto testPersonA = TestDataUtil.createTestPersonC();

        String personDtoJson = objectMapper.writeValueAsString(testPersonA);


        mockMvc.perform(
                MockMvcRequestBuilders.put("/person/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personDtoJson)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

    }






    @Test
    public void testThatDeletePersonReturnsHttpStatus204ForNonExistingPerson() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/person/2356")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());


    }

}
