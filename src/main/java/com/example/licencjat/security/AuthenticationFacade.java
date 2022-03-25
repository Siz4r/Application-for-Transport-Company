package com.example.licencjat.security;

import com.example.licencjat.userData.models.UserDataDto;

public interface AuthenticationFacade {
    UserDataDto getCurrentAuthenticatedUser();
}
