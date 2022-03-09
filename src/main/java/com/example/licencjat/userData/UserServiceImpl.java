package com.example.licencjat.userData;

import com.example.licencjat.UI.idGenerator.IdGenerator;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final IdGenerator idGenerator;
    private final EmailSenderServiceImpl emailSenderService;
    private final ModelMapper mapper;

    @Override
    public User addUser(UserServiceCommand command) {
        new UserDataValidator(userRepository).validateUserWebInput(command.getWebInput(), command.getId());

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
