package com.devvictor.user_crud_spring.services;

import com.devvictor.user_crud_spring.exceptions.NotFoundException;
import com.devvictor.user_crud_spring.models.Person;
import com.devvictor.user_crud_spring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public Person create(Person person) {
        logger.info("Create person");

        return repository.save(person);
    }

    public List<Person> findAll() {
        logger.info("FindAll person");

        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("FindById person");

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No records found for this id"));
    }

    public Person update(Person person) {
        logger.info("Update person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new NotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Delete person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No records found for this id"));

        repository.delete(entity);
    }
}
