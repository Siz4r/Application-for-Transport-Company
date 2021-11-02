package com.example.licencjat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "InputDataException")
public class IncorrectInputDataException extends IllegalArgumentException{
    public IncorrectInputDataException(String message) { super(message); }
}
