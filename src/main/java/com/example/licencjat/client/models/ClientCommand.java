package com.example.licencjat.client.models;

import com.example.licencjat.user.models.UserWebInput;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientCommand {
    private final UserWebInput webInput;
    private final String id;
}
