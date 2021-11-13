package com.example.licencjat.user.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserUpdateInput {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
}
