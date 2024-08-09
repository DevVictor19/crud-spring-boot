package com.devvictor.user_crud_spring.dtos.auth;

import java.util.Date;

public record TokenResponseDto(
        String username,
        boolean authenticated,
        Date created,
        Date expiration,
        String accessToken,
        String refreshToken
) {}
