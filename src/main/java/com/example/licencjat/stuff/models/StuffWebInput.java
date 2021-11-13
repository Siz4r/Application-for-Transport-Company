package com.example.licencjat.stuff.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
public class StuffWebInput {
    @NotBlank
    private String companyId;
    @NotNull
    @Range(min = 0, max = 10000)
    private Integer quantity;
    @NotBlank
    private String name;
    @NotNull
    @Range(min = 0, max = 999999999)
    private int prize;
    @NotNull
    private String description;
}
