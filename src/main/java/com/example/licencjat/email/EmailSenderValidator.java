package com.example.licencjat.email;

import com.example.licencjat.exceptions.IllegalArgumentExceptions.WrongEmailException;

public class EmailSenderValidator {
    public void validateEmail(String email) {
        if (!org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email)) {
            throw new WrongEmailException("Invalid email");
        }
    }
}
