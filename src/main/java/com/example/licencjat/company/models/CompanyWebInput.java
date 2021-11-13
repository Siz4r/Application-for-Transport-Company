package com.example.licencjat.company.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CompanyWebInput {
    @NotBlank
    private String name;

    @NotBlank
    private String address;
}
