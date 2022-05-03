package com.example.licencjat.employee.employeeCRUD.models;

import com.example.licencjat.orders.models.OrderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EmployeeDto {
    private String id;
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhoneNumber;

    private Set<OrderDto> orderList;
}
