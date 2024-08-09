package com.devvictor.user_crud_spring.dtos.person;

public record UpdatePersonDto(
        String firstName,
        String lastName,
        String address,
        String gender
) {}
