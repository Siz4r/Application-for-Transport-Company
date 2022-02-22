package com.example.licencjat.user;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.email.EmailSenderServiceImpl;
import com.example.licencjat.exceptions.IncorrectIdInputException;
import com.example.licencjat.user.models.User;
import com.example.licencjat.user.models.UserDto;
import com.example.licencjat.user.models.UserListDto;
import com.example.licencjat.user.models.UserServiceCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final IdGenerator idGenerator;
    private final EmailSenderServiceImpl emailSenderService;
    private final ModelMapper mapper;

    @Override
    public User addUser(UserServiceCommand command) {
        new UserDataValidator(userRepository).validateUserWebInput(command.getWebInput());

        var password = passwordEncoder.encode(command.getWebInput().getPassword());

        var id = idGenerator.generateId();

        var user = User.builder()
                .email(command.getWebInput().getEmail())
                .firstName(command.getWebInput().getFirstName())
                .lastName(command.getWebInput().getLastName())
                .phoneNumber(command.getWebInput().getPhoneNumber())
                .password(password)
                .buildingNumber(0)
                .city("")
                .postalCode("")
                .street("")
                .id(UUID.fromString(id)).build();

        userRepository.save(user);

        emailSenderService.sendEmail(command.getWebInput().getEmail());

        return user;
    }

    @Override
    public UserDto getUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new IncorrectIdInputException(""));

        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        var user = userRepository.findUserByEmail(email).orElseThrow();
        return mapper.map(user, UserDto.class);
    }

    @Override
    public String getEmail(UUID id) {
        var user = userRepository.findById(id).orElseThrow();
        return user.getEmail();
    }

    @Override
    public List<UserListDto> getUsers() {
        var users = userRepository.findAll();

        return users.stream().map(u ->
            mapper.map(u, UserListDto.class)
            ).collect(Collectors.toList());
    }

    @Override
    public void deleteAnUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateAnUser(UserServiceCommand command) {
        new UserDataValidator(userRepository).validateUserUpdateCommand(command.getUpdateInput());
        var user = userRepository.findById(command.getId()).orElseThrow(() -> new IncorrectIdInputException(""));

        mapper.map(command.getUpdateInput(), user);

        userRepository.save(user);
    }
}
