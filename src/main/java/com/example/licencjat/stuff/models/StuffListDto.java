package com.example.licencjat.stuff.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class StuffListDto {
    private UUID id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer prize;
}
