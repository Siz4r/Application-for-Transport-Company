package com.example.licencjat.stuff;

import com.example.licencjat.stuff.models.StuffDto;
import com.example.licencjat.stuff.models.StuffListDto;
import com.example.licencjat.stuff.models.StuffServiceCommand;
import com.example.licencjat.stuff.models.StuffWebInput;

import java.util.List;
import java.util.UUID;

public interface StuffService {
    void addStuff(StuffServiceCommand command);
    void editStuff(StuffServiceCommand command);
    void deleteStuff(UUID id);
    StuffDto getStuffById(UUID id);
    List<StuffListDto> getStuffs();
}
