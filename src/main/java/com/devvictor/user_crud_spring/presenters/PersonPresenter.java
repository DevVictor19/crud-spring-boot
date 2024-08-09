package com.devvictor.user_crud_spring.presenters;

import com.devvictor.user_crud_spring.dtos.person.PersonResponseDto;
import com.devvictor.user_crud_spring.models.Person;

import java.util.List;

public class PersonPresenter {
    public static PersonResponseDto toResponseObject(Person person) {
        return new PersonResponseDto(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getGender()
        );
    }

    public static List<PersonResponseDto> toResponseList(List<Person> persons) {
        return persons
                .stream()
                .map(PersonPresenter::toResponseObject)
                .toList();
    }
}
