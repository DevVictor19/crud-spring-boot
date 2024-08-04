package com.devvictor.user_crud_spring.controllers;

import com.devvictor.user_crud_spring.dtos.CreatePersonDto;
import com.devvictor.user_crud_spring.dtos.UpdatePersonDto;
import com.devvictor.user_crud_spring.models.Person;
import com.devvictor.user_crud_spring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody CreatePersonDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.create(dto));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(name = "id") Long id) {
        return personService.findById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@PathVariable(name = "id") Long id,
                         @RequestBody UpdatePersonDto dto) {
        return personService.update(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
