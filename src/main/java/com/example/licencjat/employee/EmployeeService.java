package com.example.licencjat.employee;

import com.example.licencjat.employee.models.EmployeeDto;
import com.example.licencjat.employee.models.EmployeeListDto;
import com.example.licencjat.employee.models.EmployeeServiceCommand;

import java.util.List;

interface EmployeeService {
    EmployeeDto getEmployeeById(EmployeeServiceCommand command);
    List<EmployeeListDto> getEmployees();
    void addAnEmployee(EmployeeServiceCommand command);
    void deleteAnEmployee(EmployeeServiceCommand command);
}
