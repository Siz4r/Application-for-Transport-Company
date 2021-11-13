package com.example.licencjat.client.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientListDto {
    private String clientId;
    private String userId;
    private String userFirstName;
    private String userLastName;
}
