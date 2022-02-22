package com.example.licencjat.stuff.models;

import com.example.licencjat.orders.models.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StuffDto {
    private Integer quantity;
    private String name;
    private int prize;
    private String description;
    private List<Order> orderList;
    private String companyName;
}
