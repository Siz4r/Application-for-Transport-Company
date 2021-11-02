package com.example.licencjat.user.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserServiceCommand {
    private final UserWebInput webInput;
    private final String id;
}
