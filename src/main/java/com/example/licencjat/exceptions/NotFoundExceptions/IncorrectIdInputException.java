package com.example.licencjat.exceptions.NotFoundExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IncorrectIdInputException extends IllegalArgumentException {
    public IncorrectIdInputException(String message) { super(message); }
    public IncorrectIdInputException() {super("There is no resource with such id!");}
}
