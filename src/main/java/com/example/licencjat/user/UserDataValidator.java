package com.example.licencjat.user;

import com.example.licencjat.email.EmailSenderValidator;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.IncorrectInputDataException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.IncorrectPhoneNumberException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.WrongEmailException;
import com.example.licencjat.user.models.UserUpdateInput;
import com.example.licencjat.user.models.UserWebInput;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UserDataValidator {
    private final UserRepository userRepository;

    public void validateUserWebInput(UserWebInput webInput, UUID id) {
        var validator = new EmailSenderValidator();
        validator.validateEmail(webInput.getEmail());
        if (!PhoneNumberUtil.getInstance().isPossibleNumber(webInput.getPhoneNumber(), "PL")) {
            throw new IncorrectInputDataException("Nieprawidłowy numer telefonu");
        }

        checkIfUserWithSuchEmailExists(webInput.getEmail());
        checkIfUserWithSuchPhoneNumberExists(webInput.getPhoneNumber(), id);
        checkFirstOrLastName(new String[]{webInput.getFirstName(), webInput.getLastName()});
    }

    public void validateUserUpdateCommand(UserUpdateInput updateInput, UUID id) {
        if (!PhoneNumberUtil.getInstance().isPossibleNumber(updateInput.getPhoneNumber(), "PL")) {
            throw new IncorrectPhoneNumberException("Nieprawidłowy numer telefonu");
        }
        checkIfUserWithSuchPhoneNumberExists(updateInput.getPhoneNumber(), id);
    }

    private void checkIfUserWithSuchEmailExists(String email) {
        var userData = userRepository.findUserByEmail(email);
        if (userData.isPresent()) {
            throw new WrongEmailException("Użytkownik z takim emailem już istnieje!");
        }
    }

    private void checkIfUserWithSuchPhoneNumberExists(String phoneNumber, UUID id) {
        var user = userRepository.findUserByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            if (!user.get().getId().equals(id)){
                throw new IncorrectPhoneNumberException("Istnieje już użytkownik z takim numerem telefonu!");
            }
        }
    }

    private void checkFirstOrLastName(String[] names) {
        for (var name :
                names) {
            if (!name.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid arguments!");
            }
        }
    }

}
