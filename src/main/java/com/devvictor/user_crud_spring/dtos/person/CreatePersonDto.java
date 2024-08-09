package com.devvictor.user_crud_spring.dtos.person;

public record CreatePersonDto(
        String firstName,
        String lastName,
        String address,
        String gender
) {}
