package com.example.licencjat.user.models;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class UserWebInput {
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final String email;
    @NotBlank
    private final String phoneNumber;
    @NotBlank
    private final String password;
}
