package com.example.licencjat.company.models;

import com.example.licencjat.stuff.models.Stuff;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDto {
    private String name;
    private String address;
    private List<Stuff> stuffList;
}
