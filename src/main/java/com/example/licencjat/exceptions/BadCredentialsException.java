package com.example.licencjat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("User with provided credentials doesn't exist");
    }
}