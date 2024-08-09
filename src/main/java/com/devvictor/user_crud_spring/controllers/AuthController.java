package com.devvictor.user_crud_spring.controllers;

import com.devvictor.user_crud_spring.dtos.auth.LoginUserDto;
import com.devvictor.user_crud_spring.dtos.auth.TokenResponseDto;
import com.devvictor.user_crud_spring.exceptions.BadRequestException;
import com.devvictor.user_crud_spring.services.AuthService;
import com.devvictor.user_crud_spring.utils.AppMediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication endpoint")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(name = "/login", consumes = AppMediaType.JSON, produces = AppMediaType.JSON)
    @Operation(summary = "Authenticates a user and returns a token")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginUserDto dto) {
        if (dto == null || dto.username() == null
                || dto.password() == null || dto.username().isBlank()
                || dto.password().isBlank()) {
            throw new BadRequestException("Missing username or password");
        }

        return authService.login(dto);
    }
}
