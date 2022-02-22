package com.example.licencjat.orders.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderListDto {
    private UUID orderId;
    private String stuffName;
    private int amount;
}
