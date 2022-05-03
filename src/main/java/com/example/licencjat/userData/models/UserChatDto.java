package com.example.licencjat.userData.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserChatDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
