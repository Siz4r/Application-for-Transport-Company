package com.example.licencjat.orders.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class OrderCommand {
    private final UUID orderId;
    private final UUID stuffId;
    private final UUID clientId;
    private final OrderWebInput webInput;
}
