package com.example.licencjat.company;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.company.models.Company;
import com.example.licencjat.company.models.CompanyDto;
import com.example.licencjat.company.models.CompanyListDto;
import com.example.licencjat.company.models.CompanyServiceCommand;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final IdGenerator idGenerator;
    private final CompanyRepository repository;
    private final ModelMapper mapper;

    @Override
    public CompanyDto getCompanyById(CompanyServiceCommand command) {
        var company = repository.findById(command.getId()).orElseThrow(IncorrectIdInputException::new);

        return mapper.map(company, CompanyDto.class);
    }

    @Override
    public List<CompanyListDto> getCompanies() {
        var companies = repository.findAll();

        return companies.stream()
                .map(c -> mapper.map(c, CompanyListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompany(CompanyServiceCommand command) {
        repository.deleteById(command.getId());
    }

    @Override
    public UUID addCompany(CompanyServiceCommand command) {
        var company = mapper.map(command.getWebInput(), Company.class);

        var id = UUID.fromString(idGenerator.generateId());
        company.setId(id);

        repository.save(company);

        return id;
    }
}
