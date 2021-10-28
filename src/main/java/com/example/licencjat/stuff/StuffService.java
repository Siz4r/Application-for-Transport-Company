package com.example.licencjat.stuff;

import com.example.licencjat.stuff.models.StuffDto;
import com.example.licencjat.stuff.models.StuffListDto;
import com.example.licencjat.stuff.models.StuffWebInput;

import java.util.List;

public interface StuffService {
    void addStuff(StuffWebInput command);
    void editStuff(StuffWebInput command);
    void deleteStuff(StuffWebInput command);
    StuffDto getStuffById(String id);
    List<StuffListDto> getStuffs();
}
