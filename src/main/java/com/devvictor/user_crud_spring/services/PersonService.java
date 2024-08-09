package com.devvictor.user_crud_spring.services;

import com.devvictor.user_crud_spring.dtos.person.CreatePersonDto;
import com.devvictor.user_crud_spring.dtos.person.UpdatePersonDto;
import com.devvictor.user_crud_spring.exceptions.NotFoundException;
import com.devvictor.user_crud_spring.exceptions.RequiredObjectIsNullException;
import com.devvictor.user_crud_spring.mappers.ApplicationModelMapper;
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

    public Person create(CreatePersonDto dto) {
        logger.info("Create person");

        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }

        return repository.save(ApplicationModelMapper.parseObject(dto, Person.class));
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

    public Person update(Long id, UpdatePersonDto dto) {
        logger.info("Update person");

        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }

        Person entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No records found for this id"));

        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setAddress(dto.address());
        entity.setGender(dto.gender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Delete person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No records found for this id"));

        repository.delete(entity);
    }
}
