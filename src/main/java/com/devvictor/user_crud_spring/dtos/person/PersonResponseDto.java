package com.devvictor.user_crud_spring.dtos.person;

public record PersonResponseDto (
         Long id,
         String firstName,
         String lastName,
         String address,
         String gender
) {}