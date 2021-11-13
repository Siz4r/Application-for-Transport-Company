package com.example.licencjat.employee.models;

import com.example.licencjat.files.models.File;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmployeeDto {
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;

    private List<File> userFiles = new ArrayList<>();
}
