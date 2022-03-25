package com.example.licencjat.company;

import com.example.licencjat.company.models.CompanyDto;
import com.example.licencjat.company.models.CompanyListDto;
import com.example.licencjat.company.models.CompanyServiceCommand;

import java.util.List;

interface CompanyService {
    CompanyDto getCompanyById(CompanyServiceCommand command);
    List<CompanyListDto> getCompanies();
    void deleteCompany(CompanyServiceCommand command);
    void addCompany(CompanyServiceCommand command);
//    void getCompanysWithStuffs();
}
