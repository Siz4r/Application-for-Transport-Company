package com.example.licencjat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "No cos sie popsulo sie")
public class WrongEmailException extends IllegalArgumentException {
    public WrongEmailException(String message) {
        super("cos cos sie popsulo nie bylo mnie slychac");
    }
}
