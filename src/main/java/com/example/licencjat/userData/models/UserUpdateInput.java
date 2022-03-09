package com.example.licencjat.userData.models;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UserUpdateInput {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String postalCode;
    @NotNull
    @Range(min = 0, max = 999)
    private Integer buildingNumber;
}
