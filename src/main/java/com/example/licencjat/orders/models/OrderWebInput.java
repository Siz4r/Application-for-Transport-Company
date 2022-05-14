package com.example.licencjat.orders.models;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
public class OrderWebInput {
    @NotNull
    @Range(min = 0, max = 100000)
    private int amount;
}
