package com.example.licencjat.client.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientOrderDto {
    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;
    private String userStreet;
    private String userCity;
    private String userPostalCode;
    private Integer userBuildingNumber;
}
