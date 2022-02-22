package com.example.licencjat.employee.employeeCRUD.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeListDto {
    private UUID employeeId;
    private String userFirstName;
    private String userLastName;
    private Boolean isAvailable;
}
