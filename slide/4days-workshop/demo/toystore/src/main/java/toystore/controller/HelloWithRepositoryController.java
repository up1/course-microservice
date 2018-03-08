package toystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toystore.domain.Hello;
import toystore.repository.Person;
import toystore.repository.PersonRepository;

import java.util.Optional;

@RestController
public class HelloWithRepositoryController {

    private PersonRepository personRepository;

    @Autowired
    public HelloWithRepositoryController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/hello/data/{name}")
    public Hello sayHi(@PathVariable String name) {
        Optional<Person> person
                = personRepository.findByFirstName(name);

        String message =
                person.map(person1 ->
                        String.format("Hello %s", person1.getFirstName()))
                        .orElse("Data not found");

        return new Hello(message);
    }

}
