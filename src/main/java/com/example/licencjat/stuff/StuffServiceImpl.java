package com.example.licencjat.stuff;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.company.CompanyRepository;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.stuff.models.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StuffServiceImpl implements StuffService{
    private final StuffRepository stuffRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;
    private final CompanyRepository companyRepository;

    @Override
    public UUID addStuff(StuffServiceCommand command) {
        var company = companyRepository.findById(command.getWebInput().getCompanyId()).orElseThrow(IncorrectIdInputException::new);

        var stuff = mapper.map(command.getWebInput(), Stuff.class);

        var id = UUID.fromString(idGenerator.generateId());
        stuff.setId(id);

        company.addStuff(stuff);

        companyRepository.save(company);

        return id;
    }

    @Override
    public void editStuff(StuffServiceCommand command) {
        var stuff = stuffRepository.findById(command.getId()).orElseThrow(IncorrectIdInputException::new);
        var stuffs = stuffRepository.findAll();

        for (var s:
                stuffs) {
            System.out.println(s.getName());
        }
        var index = stuffs.indexOf(stuff);
        stuff.setQuantity(command.getUpdateCommand().getQuantity());
        stuff.setPrize(command.getUpdateCommand().getPrize());
        stuffs.set(index, stuff);

        for (var s:
             stuffs) {
            System.out.println(s.getName());
        }

        stuffRepository.saveAll(stuffs);
    }

    @Override
    public void deleteStuff(UUID id) {
        stuffRepository.deleteById(id);
    }

    @Override
    public StuffDto getStuffById(UUID id) {
        var stuff = stuffRepository.findById(id).orElseThrow(IncorrectIdInputException::new);
        return mapper.map(stuff, StuffDto.class);
    }

    @Override
    public List<StuffListDto> getStuffs() {
        var stuffs = stuffRepository.findAll();

        return stuffs.stream().map(
                s -> mapper.map(s, StuffListDto.class))
                .collect(Collectors.toList());
    }
}
