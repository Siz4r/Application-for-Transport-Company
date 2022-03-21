package com.example.licencjat.company.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyListDto {
    private UUID id;
    private String name;
    private String city;
    private String street;
    private String postalCode;
    private Integer buildingNumber;
}
