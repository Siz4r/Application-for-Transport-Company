package com.example.licencjat.security;

import com.example.licencjat.userData.models.UserDataDto;

import java.util.List;

public interface AuthenticationFacade {
    UserDataDto getCurrentAuthenticatedUser();
    List<String> getCurrentAuthenticatedUserAuthorities();
    Boolean isCurrentAuthenticatedUserAnAdmin();
}
