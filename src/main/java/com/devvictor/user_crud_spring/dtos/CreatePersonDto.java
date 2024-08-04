package com.devvictor.user_crud_spring.dtos;

public record CreatePersonDto(
        String firstName,
        String lastName,
        String address,
        String gender) {
}
