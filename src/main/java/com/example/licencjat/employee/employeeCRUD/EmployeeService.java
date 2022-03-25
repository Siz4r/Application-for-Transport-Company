package com.example.licencjat.employee.employeeCRUD;

import com.example.licencjat.employee.employeeCRUD.models.EmployeeDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeListDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeServiceCommand;

import java.util.List;
import java.util.UUID;

interface EmployeeService {
    EmployeeDto getEmployeeById(EmployeeServiceCommand command);
    List<EmployeeListDto> getEmployees();
    UUID addAnEmployee(EmployeeServiceCommand command);

    UUID addAnAdmin(EmployeeServiceCommand command);

    void deleteAnEmployee(EmployeeServiceCommand command);
}
