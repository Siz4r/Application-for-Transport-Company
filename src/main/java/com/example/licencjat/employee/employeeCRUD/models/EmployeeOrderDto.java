package com.example.licencjat.employee.employeeCRUD.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeOrderDto {
    private UUID employeeId;
    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;

    public EmployeeOrderDto() {
        employeeId = UUID.randomUUID();
        userFirstName = "";
        userLastName = "";
        userPhoneNumber = "";
    }
}
