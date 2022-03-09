package com.example.licencjat.orders.models;

import com.example.licencjat.client.models.ClientOrderDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeOrderDto;
import com.example.licencjat.stuff.models.StuffOrderDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OrderDetailsDto {
    private String id;
    private ClientOrderDto client;
    private EmployeeOrderDto employee;
    private StuffOrderDto stuff;
    private Timestamp date;
    private boolean isDone;
    private int amount;
}
