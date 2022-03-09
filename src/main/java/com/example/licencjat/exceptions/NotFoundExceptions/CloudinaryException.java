package com.example.licencjat.exceptions.NotFoundExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CloudinaryException extends IllegalArgumentException {
    public CloudinaryException(String message) { super(message); }
}

