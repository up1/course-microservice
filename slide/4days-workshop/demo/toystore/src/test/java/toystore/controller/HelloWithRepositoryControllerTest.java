package toystore.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toystore.domain.Hello;
import toystore.repository.Person;
import toystore.repository.PersonRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class HelloWithRepositoryControllerTest {

    @Mock
    private PersonRepository personRepository;

    private HelloWithRepositoryController controller;

    @Before
    public void init() {
        initMocks(this);
        controller
                = new HelloWithRepositoryController(personRepository);
    }

    @Test
    public void shouldReturnHelloSomkiat() {
        // Arrange
        Person somkiat = new Person("somkiat", "pui");
        given(personRepository.findByFirstName("somkiat"))
                .willReturn(Optional.of(somkiat));

        // Act
        Hello hello = controller.sayHi("somkiat");

        // Assert
        assertEquals("Hello somkiat", hello.getMessage());


    }

}