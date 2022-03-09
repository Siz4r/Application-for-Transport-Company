package com.example.licencjat.client.models;

import com.example.licencjat.userData.models.UserWebInput;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ClientCommand {
    private final UserWebInput webInput;
    private final UUID id;
}
