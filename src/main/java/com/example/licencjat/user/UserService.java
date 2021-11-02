package com.example.licencjat.user;

import com.example.licencjat.user.models.UserDto;
import com.example.licencjat.user.models.UserListDto;
import com.example.licencjat.user.models.UserServiceCommand;

import java.util.List;

interface UserService {
    void addUser(UserServiceCommand command);
    UserDto getUserById(String id);
    List<UserListDto> getUsers();
    void deleteAnUser(String id);
    void updateAnUser(UserServiceCommand command);
}
