package com.example.licencjat.company.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyListDto {
    private UUID id;
    private String name;
}
