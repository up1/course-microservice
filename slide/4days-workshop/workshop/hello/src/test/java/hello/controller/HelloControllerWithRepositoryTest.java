package hello.controller;

import hello.domain.Hello;
import hello.repository.Person;
import hello.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class HelloControllerWithRepositoryTest {

    private HelloControllerWithRepository controllerWithRepository;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void init() {
        initMocks(this);
        controllerWithRepository
                = new HelloControllerWithRepository(personRepository);
    }

    @Test
    public void shouldReturnHelloSomkiat() {
        //Arrange
        Person somkiat = new Person("somkiat", "pui");
        given(personRepository.findByFirstName("somkiat"))
                .willReturn(Optional.of(somkiat));

        // Action
        Hello hello = controllerWithRepository.sayHi("somkiat");

        // Assert
        assertEquals("Hello somkiat", hello.getMessage());
    }

}