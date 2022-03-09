package com.example.licencjat.userData.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserServiceCommand {
    private final UserWebInput webInput;
    private final UserUpdateInput updateInput;
    private final UUID id;
}
