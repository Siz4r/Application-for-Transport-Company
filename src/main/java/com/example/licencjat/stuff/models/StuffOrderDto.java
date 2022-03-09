package com.example.licencjat.stuff.models;

import com.example.licencjat.company.models.CompanyStuffDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StuffOrderDto {
    private String name;
    private CompanyStuffDto company;
}
