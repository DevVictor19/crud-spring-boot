package com.devvictor.user_crud_spring.exceptions;

import java.io.Serializable;
import java.util.Date;

public record ExceptionResponse(
        Date timestamp,
        String message,
        String details) implements Serializable {
}
