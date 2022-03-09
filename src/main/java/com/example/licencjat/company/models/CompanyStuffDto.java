package com.example.licencjat.company.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyStuffDto {
    private String name;
    private String city;
    private String street;
    private String postalCode;
    private Integer buildingNumber;
}
