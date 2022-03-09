package com.example.licencjat.exceptions;

import com.example.licencjat.authentication.ExpiredTokenException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.IncorrectInputDataException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.IncorrectPhoneNumberException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.WrongEmailException;
import com.example.licencjat.exceptions.NotFoundExceptions.CloudinaryException;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/*
 * Catch and handle exceptions thrown by controllers.
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({WrongEmailException.class, IncorrectPhoneNumberException.class, IncorrectInputDataException.class})
    public ResponseEntity<Object> registerSimpleIllegalArgumentsExceptions(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({IncorrectIdInputException.class, CloudinaryException.class})
    public ResponseEntity<Object> registerSimpleNotFoundExceptions(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> body = new HashMap<>();
        for (FieldError e : exception.getFieldErrors()) {
            body.put(e.getField(), e.getDefaultMessage());
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ExpiredTokenException.class})
    public ResponseEntity<Object> handleExpiredToken(ExpiredTokenException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}