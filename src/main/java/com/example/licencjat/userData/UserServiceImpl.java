package com.example.licencjat.userData;

import com.example.licencjat.UI.PasswordService;
import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.authorities.AuthorityRepository;
import com.example.licencjat.authorities.models.AuthorityGroup;
import com.example.licencjat.authorities.models.ROLES;
import com.example.licencjat.email.EmailSenderServiceImpl;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.IncorrectInputDataException;
import com.example.licencjat.userData.models.User;
import com.example.licencjat.userData.models.UserDataDto;
import com.example.licencjat.userData.models.UserDataListDto;
import com.example.licencjat.userData.models.UserServiceCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final PasswordService passwordService;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final IdGenerator idGenerator;
    private final EmailSenderServiceImpl emailSenderService;
    private final ModelMapper mapper;

    @Override
    public User addUser(UserServiceCommand command, ROLES role) {
        new UserDataValidator(userRepository).validateUserWebInput(command.getWebInput(), command.getId());

        var password = passwordService.alphaNumericString(20);
        var encodedPassword = passwordEncoder.encode(password);

        var id = idGenerator.generateId();

        var user = User.builder()
                .email(command.getWebInput().getEmail())
                .firstName(command.getWebInput().getFirstName())
                .lastName(command.getWebInput().getLastName())
                .phoneNumber(command.getWebInput().getPhoneNumber())
                .password(encodedPassword)
                .buildingNumber(0)
                .city("")
                .postalCode("")
                .street("")
                .id(UUID.fromString(id)).build();

        var authorityGroup = switch (role) {
            case ADMIN -> authorityRepository.findByCode("A00").orElseThrow(IncorrectIdInputException::new);
            case EMPLOYEE -> authorityRepository.findByCode("E00").orElseThrow(IncorrectIdInputException::new);
            case CLIENT -> authorityRepository.findByCode("C00").orElseThrow(IncorrectIdInputException::new);
        };

        var authorities = List.of(authorityGroup);
        user.setUserGroups(authorities);

        userRepository.save(user);

        var message = "Hi!\n" +
                "You have been registered on Trans-Mat page. We are pleased that you want to work with us!\n" +
                "This is your password: " + password +
                "\n Have a nice day!";

        emailSenderService.sendEmail(command.getWebInput().getEmail(), "Trans-Mat Registration", message);

        return user;
    }

    @Override
    public UserDataDto getUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(IncorrectIdInputException::new);

        return mapper.map(user, UserDataDto.class);
    }

    @Override
    public UserDataDto getUserByEmail(String email) {
        var user = userRepository.findUserByEmail(email).orElseThrow();
        return mapper.map(user, UserDataDto.class);
    }

    @Override
    public String getEmail(UUID id) {
        var user = userRepository.findById(id).orElseThrow();
        return user.getEmail();
    }

    @Override
    public List<UserDataListDto> getUsers() {
        var users = userRepository.findAll();

        return users.stream().map(u ->
            mapper.map(u, UserDataListDto.class)
            ).collect(Collectors.toList());
    }

    @Override
    public void deleteAnUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateAnUser(UserServiceCommand command) {
        new UserDataValidator(userRepository).validateUserUpdateCommand(command.getUpdateInput(), command.getId());
        var user = userRepository.findById(command.getId()).orElseThrow(IncorrectIdInputException::new);

        mapper.map(command.getUpdateInput(), user);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new IncorrectInputDataException("Wrong username"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
