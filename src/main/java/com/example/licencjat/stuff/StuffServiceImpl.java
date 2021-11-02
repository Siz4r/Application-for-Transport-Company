package com.example.licencjat.stuff;

import com.example.licencjat.exceptions.IncorrectIdInputException;
import com.example.licencjat.stuff.models.*;
import com.example.licencjat.user.IdGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StuffServiceImpl implements StuffService{
    private final StuffRepository stuffRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;

    @Override
    public void addStuff(StuffServiceCommand command) {
        var id = idGenerator.generateId();

        stuffRepository.save(Stuff.builder()
                .Id(id)
                .name(command.getWebInput().getName())
                .prize(command.getWebInput().getPrize())
                .quantity(command.getWebInput().getQuantity()).build());
    }

    @Override
    public void editStuff(StuffServiceCommand command) {
        var stuff = stuffRepository.findById(command.getId()).orElseThrow(() -> new IncorrectIdInputException("Error"));

        stuff.setName(command.getWebInput().getName());
        stuff.setPrize(command.getWebInput().getPrize());
        stuff.setQuantity(command.getWebInput().getQuantity());

        stuffRepository.save(stuff);
    }

    @Override
    public void deleteStuff(String id) {
        stuffRepository.deleteById(id);
    }

    @Override
    public StuffDto getStuffById(String id) {
        var stuff = stuffRepository.findById(id).orElseThrow(() -> new IncorrectIdInputException("Error"));
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
