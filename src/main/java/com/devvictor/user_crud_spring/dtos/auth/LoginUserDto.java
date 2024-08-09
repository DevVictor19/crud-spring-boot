package com.devvictor.user_crud_spring.dtos.auth;

public record LoginUserDto (
        String username,
        String password
) {}
