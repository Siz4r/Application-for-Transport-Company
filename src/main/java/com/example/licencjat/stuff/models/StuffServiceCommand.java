package com.example.licencjat.stuff.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StuffServiceCommand {
    private StuffWebInput webInput;
    private String id;
}
