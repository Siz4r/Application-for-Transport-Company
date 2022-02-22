package com.example.licencjat.employee.employeeCRUD.models;

import com.example.licencjat.user.models.UserWebInput;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class EmployeeServiceCommand {
    private final UUID id;
    private final UserWebInput webInput;
}
