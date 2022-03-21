package com.example.licencjat.company.models;

import com.example.licencjat.stuff.models.Stuff;
import com.example.licencjat.stuff.models.StuffListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDto {
    private String id;
    private String name;
    private String city;
    private String street;
    private String postalCode;
    private Integer buildingNumber;
    private List<StuffListDto> stuffList;
}
