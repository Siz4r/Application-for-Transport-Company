package com.example.licencjat.stuff.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class StuffServiceCommand {
    private final StuffUpdateCommand updateCommand;
    private final StuffWebInput webInput;
    private final UUID id;
}
