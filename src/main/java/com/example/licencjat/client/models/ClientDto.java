package com.example.licencjat.client.models;

import com.example.licencjat.orders.models.OrderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ClientDto {
    private UUID id;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhoneNumber;

    private Set<OrderDto> orderList;
}
