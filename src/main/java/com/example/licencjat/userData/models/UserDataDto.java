package com.example.licencjat.userData.models;

import com.example.licencjat.authorities.models.AuthorityIdDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class UserDataDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    private String street;
    private String postalCode;
    private Integer buildingNumber;
}
