package com.example.licencjat.exceptions.IllegalArgumentExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectPhoneNumberException extends IllegalArgumentException{
    public IncorrectPhoneNumberException(String message) { super(message); }
}
