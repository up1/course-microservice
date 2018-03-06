package hello.controller;

import hello.domain.Hello;
import hello.repository.Person;
import hello.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public class HelloControllerWithRepository {

    private final PersonRepository personRepository;

    @Autowired
    public HelloControllerWithRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/hello/data/{name}")
    public Hello sayHi(@PathVariable String name) {
        Optional<Person> foundPerson = personRepository.findByFirstName(name);
        String result = foundPerson
                .map(person -> String.format("Hello %s", person.getFirstName()))
                .orElse("Data not found");
        return new Hello(result);
    }

}

