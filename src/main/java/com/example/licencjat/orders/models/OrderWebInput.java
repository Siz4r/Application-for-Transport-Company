package com.example.licencjat.orders.models;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
public class OrderWebInput {
    @NotNull
    @Range(min = 1, max = 100)
    private int amount;
}
