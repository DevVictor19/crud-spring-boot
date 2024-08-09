package com.devvictor.user_crud_spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException {
    public InvalidJwtAuthenticationException() {
        super("Invalid jwt authentication");
    }

    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
