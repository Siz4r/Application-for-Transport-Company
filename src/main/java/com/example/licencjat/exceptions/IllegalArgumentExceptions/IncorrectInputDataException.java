package com.example.licencjat.exceptions.IllegalArgumentExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectInputDataException extends IllegalArgumentException{
    public IncorrectInputDataException(String message) { super(message); }
}
