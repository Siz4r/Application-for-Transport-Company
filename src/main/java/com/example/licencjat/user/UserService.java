package com.example.licencjat.user;

import com.example.licencjat.user.models.User;
import com.example.licencjat.user.models.UserDto;
import com.example.licencjat.user.models.UserListDto;
import com.example.licencjat.user.models.UserServiceCommand;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User addUser(UserServiceCommand command);
    UserDto getUserById(UUID id);
    UserDto getUserByEmail(String email);
    String getEmail(UUID id);
    List<UserListDto> getUsers();
    void deleteAnUser(UUID id);
    void updateAnUser(UserServiceCommand command);
}
