package com.example.licencjat.security;

import com.example.licencjat.exceptions.ForbiddenException;
import com.example.licencjat.userData.UserService;
import com.example.licencjat.userData.models.UserDataDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private final UserService userService;

    @Override
    public UserDataDto getCurrentAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            throw new ForbiddenException();
        }

        return userService.getUserByEmail(authentication.getName());
    }
}
