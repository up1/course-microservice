package toystore.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldFoundData() throws Exception {
        // Arrange
        Person somkiat = new Person("somkiat", "pui");
        personRepository.save(somkiat);

        // Act
        Optional<Person> person
                = personRepository.findByFirstName("somkiat");

        // Assert
        assertEquals("somkiat", person.get().getFirstName());
        assertEquals("pui", person.get().getLastName());

    }

}