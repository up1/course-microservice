package toystore.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import toystore.repository.Person;
import toystore.repository.PersonRepository;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloWithRepositoryController.class)
public class HelloWithRepositoryIntegrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void shouldReturnHelloSomkiat() throws Exception {

        // Arrange
        Person somkiat = new Person("somkiat", "pui");
        given(personRepository.findByFirstName("somkiat"))
                .willReturn(Optional.of(somkiat));

        // Act and Assert
        mockMvc.perform(get("/hello/data/somkiat"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.message")
                        .value("Hello somkiat"));
    }

}