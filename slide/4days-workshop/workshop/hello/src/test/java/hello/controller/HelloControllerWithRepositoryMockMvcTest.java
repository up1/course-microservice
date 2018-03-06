package hello.controller;

import hello.repository.Person;
import hello.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloControllerWithRepository.class)
public class HelloControllerWithRepositoryMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void shouldReturnHelloSomkiat() throws Exception {
        //Arrange
        Person somkiat = new Person("somkiat", "pui");
        given(personRepository.findByFirstName("somkiat"))
                .willReturn(Optional.of(somkiat));

        // Action and Assert
        mockMvc.perform(get("/hello/data/somkiat"))
                .andExpect(
                        jsonPath("$.message")
                                .value("Hello somkiat"))
                .andExpect(status().is2xxSuccessful());
    }


}