package com.example.licencjat.company.models;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CompanyWebInput {
    @NotBlank
    private String name;

    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String postalCode;
    @NotNull
    @Range(min = 1, max = 999)
    private Integer buildingNumber;
}
