package com.devvictor.user_crud_spring.controllers;

import com.devvictor.user_crud_spring.dtos.CreatePersonDto;
import com.devvictor.user_crud_spring.dtos.UpdatePersonDto;
import com.devvictor.user_crud_spring.models.Person;
import com.devvictor.user_crud_spring.services.PersonService;
import com.devvictor.user_crud_spring.utils.AppMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(consumes = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML },
            produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
    public ResponseEntity<Person> create(@RequestBody CreatePersonDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.create(dto));
    }

    @GetMapping(produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
    public Person findById(@PathVariable(name = "id") Long id) {
        return personService.findById(id);
    }

    @PutMapping(value = "/{id}", consumes = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML },
            produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
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
