package toystore.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository
        extends CrudRepository<Person, Integer>{

    Optional<Person> findByFirstName(String name);

}
