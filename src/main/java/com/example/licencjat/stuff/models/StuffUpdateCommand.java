package com.example.licencjat.stuff.models;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class StuffUpdateCommand {
    @NotNull
    @Range(min = 0, max = 10000)
    private Integer quantity;
    @NotNull
    @Range(min = 0, max = 999999999)
    private int prize;
}
