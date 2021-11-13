package com.example.licencjat.user;

import com.example.licencjat.email.EmailSenderValidator;
import com.example.licencjat.exceptions.IncorrectInputDataException;
import com.example.licencjat.exceptions.WrongEmailException;
import com.example.licencjat.user.models.UserUpdateInput;
import com.example.licencjat.user.models.UserWebInput;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDataValidator {
    private final UserRepository userRepository;

    public void validateUserWebInput(UserWebInput webInput) {
        var validator = new EmailSenderValidator();
        validator.validateEmail(webInput.getEmail());
        if (!PhoneNumberUtil.getInstance().isPossibleNumber(webInput.getPhoneNumber(), "PL")) {
            throw new IncorrectInputDataException("Wrong phone number");
        }

        checkIfUserWithSuchEmailExists(webInput.getEmail());
        checkIfUserWithSuchPhoneNumberExists(webInput.getPhoneNumber());
        checkFirstOrLastName(new String[]{webInput.getFirstName(), webInput.getLastName()});
    }

    public void validateUserUpdateCommand(UserUpdateInput updateInput) {
        if (!PhoneNumberUtil.getInstance().isPossibleNumber(updateInput.getPhoneNumber(), "PL")) {
            throw new IncorrectInputDataException("Wrong phone number");
        }
        checkIfUserWithSuchPhoneNumberExists(updateInput.getPhoneNumber());
        checkFirstOrLastName(new String[]{updateInput.getFirstName(), updateInput.getLastName()});
    }

    private void checkIfUserWithSuchEmailExists(String email) {
        var userData = userRepository.findUserByEmail(email);
        if (userData.isPresent()) {
            throw new WrongEmailException("User with such email already exists!");
        }
    }

    private void checkIfUserWithSuchPhoneNumberExists(String username) {
        var user = userRepository.findUserByPhoneNumber(username);
        if (user.isPresent()) {
            throw new IncorrectInputDataException("There is already user with such username!");
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
