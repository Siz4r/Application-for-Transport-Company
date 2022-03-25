package com.example.licencjat.userData;

import com.example.licencjat.authorities.models.ROLES;
import com.example.licencjat.userData.models.User;
import com.example.licencjat.userData.models.UserDataDto;
import com.example.licencjat.userData.models.UserDataListDto;
import com.example.licencjat.userData.models.UserServiceCommand;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    User addUser(UserServiceCommand command, ROLES role);

    UserDataDto getUserById(UUID id);
    UserDataDto getUserByEmail(String email);
    String getEmail(UUID id);
    List<UserDataListDto> getUsers();
    void deleteAnUser(UUID id);
    void updateAnUser(UserServiceCommand command);

    UserDetails loadUserByUsername(String username);
}
