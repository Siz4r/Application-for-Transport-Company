package com.example.licencjat.orders.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private String id;
    private String clientUserFirstName;
    private String clientUserLastName;
    private String employeeUserFirstName;
    private String employeeUserLastName;
    private String stuffName;
    private String stuffCompanyName;
    private boolean isDone;
    private int amount;
}
