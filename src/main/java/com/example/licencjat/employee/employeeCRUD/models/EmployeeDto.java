package com.example.licencjat.employee.employeeCRUD.models;

import com.example.licencjat.files.models.File;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.orders.models.OrderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class EmployeeDto {
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;

    private List<File> userFiles = new ArrayList<>();
    private Set<OrderDto> employeeOrderList;
}
