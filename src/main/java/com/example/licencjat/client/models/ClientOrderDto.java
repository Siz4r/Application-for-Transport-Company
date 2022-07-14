package com.example.licencjat.client.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ClientOrderDto {
    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;
    private String userStreet;
    private String userCity;
    private String userPostalCode;
    private Integer userBuildingNumber;
}
