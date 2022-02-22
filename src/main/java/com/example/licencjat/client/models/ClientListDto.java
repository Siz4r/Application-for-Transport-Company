package com.example.licencjat.client.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClientListDto {
    private UUID clientId;
    private UUID userId;
    private String userFirstName;
    private String userLastName;
}
