package com.example.licencjat.orders.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderListDto {
    private UUID id;
    private boolean isDone;
    private String stuffName;
    private String stuffCompanyName;
    private String clientUserFirstName;
    private String clientUserLastName;
    private String employeeUserFirstName;
    private String employeeUserLastName;
}
