package com.example.licencjat.user;

import com.example.licencjat.email.EmailSenderServiceImpl;
import com.example.licencjat.user.models.User;
import com.example.licencjat.user.models.UserDto;
import com.example.licencjat.user.models.UserListDto;
import com.example.licencjat.user.models.UserServiceCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void addUser(UserServiceCommand command) {
        new UserDataValidator(userRepository).validateUserCommand(command.getWebInput());

        var password = passwordEncoder.encode(command.getWebInput().getPassword());

        var id = idGenerator.generateId();

        userRepository.save(
                User.builder()
                        .email(command.getWebInput().getEmail())
                        .firstName(command.getWebInput().getFirstName())
                        .lastName(command.getWebInput().getLastName())
                        .phoneNumber(command.getWebInput().getPhoneNumber())
                        .password(password)
                        .id(id).build()
        );

        emailSenderService.sendEmail(command.getWebInput().getEmail());
    }

    @Override
    public UserDto getUserById(String id) {
        return null;
    }

    @Override
    public List<UserListDto> getUsers() {
        var users = userRepository.findAll();

        return users.stream().map(u -> mapper.map(u, UserListDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAnUser(String id) {

    }

    @Override
    public void updateAnUser(UserServiceCommand command) {

    }
}
