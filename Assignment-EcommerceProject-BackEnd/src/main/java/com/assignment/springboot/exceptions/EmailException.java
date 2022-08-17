package com.assignment.springboot.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailException extends RuntimeException {
    private final String emailError;

    public EmailException(String emailError) {
        this.emailError = emailError;
    }
}
