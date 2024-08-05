package com.devvictor.user_crud_spring.presenters;

import com.devvictor.user_crud_spring.dtos.PersonResponseDto;
import com.devvictor.user_crud_spring.models.Person;

import java.util.List;

public class PersonPresenter {
    public static PersonResponseDto toResponseObject(Person person) {
        var entity = new PersonResponseDto();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }

    public static List<PersonResponseDto> toResponseList(List<Person> persons) {
        return persons
                .stream()
                .map(PersonPresenter::toResponseObject)
                .toList();
    }
}
