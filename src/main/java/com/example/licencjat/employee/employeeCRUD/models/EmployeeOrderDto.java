package com.example.licencjat.employee.employeeCRUD.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class EmployeeOrderDto {
    private UUID employeeId;
    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;
}
