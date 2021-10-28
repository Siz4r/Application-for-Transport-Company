package com.example.licencjat.stuff;

import com.example.licencjat.exceptions.IncorrectIdInputException;
import com.example.licencjat.stuff.models.Stuff;
import com.example.licencjat.stuff.models.StuffDto;
import com.example.licencjat.stuff.models.StuffListDto;
import com.example.licencjat.stuff.models.StuffWebInput;
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
    public void addStuff(StuffWebInput webInput) {
        var id = idGenerator.generateId();

        stuffRepository.save(Stuff.builder()
                .Id(id)
                .name(webInput.getName())
                .prize(webInput.getPrize())
                .quantity(webInput.getQuantity()).build());
    }

    @Override
    public void editStuff(StuffWebInput webInput) {

    }

    @Override
    public void deleteStuff(StuffWebInput webInput) {

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
