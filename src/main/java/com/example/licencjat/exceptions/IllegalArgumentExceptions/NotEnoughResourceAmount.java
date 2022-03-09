package com.example.licencjat.exceptions.IllegalArgumentExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEnoughResourceAmount extends IllegalArgumentException{
    public NotEnoughResourceAmount(String message) { super(message); }
}
