package com.example.licencjat.authentication.models;

import com.example.licencjat.user.models.UserDataDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AuthenticationDto extends PublicTokenDto{
    private final UserDataDto user;
}
