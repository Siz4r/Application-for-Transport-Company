package com.example.licencjat.orders.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCommand {
    private final String orderId;
    private final String stuffId;
    private final String clientId;
    private final OrderWebInput webInput;
}
