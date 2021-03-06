package com.example.licencjat.user;

import com.example.licencjat.user.authorities.models.ROLES;
import com.example.licencjat.user.models.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    User addUser(UserServiceCommand command, ROLES role);

    UserDataDto getUserById(UUID id);
    UserDataDto getUserByEmail(String email);
    String getEmail(UUID id);
    List<UserChatDto> getUsers();
    void deleteAnUser(UUID id);
    void updateAnUser(UserServiceCommand command);

    UserDetails loadUserByUsername(String username);
}
