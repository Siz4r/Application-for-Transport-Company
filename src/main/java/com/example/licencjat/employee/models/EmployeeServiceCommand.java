package com.example.licencjat.employee.models;

import com.example.licencjat.user.models.UserWebInput;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeServiceCommand {
    private final String id;
    private final UserWebInput webInput;
}
