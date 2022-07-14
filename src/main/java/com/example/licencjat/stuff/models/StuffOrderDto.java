package com.example.licencjat.stuff.models;

import com.example.licencjat.company.models.CompanyStuffDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StuffOrderDto {
    private String name;
    private CompanyStuffDto company;
}
