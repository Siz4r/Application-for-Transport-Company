package com.example.licencjat.employee.employeeCRUD;

import com.example.licencjat.employee.employeeCRUD.models.EmployeeDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeListDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeServiceCommand;

import java.util.List;

interface EmployeeService {
    EmployeeDto getEmployeeById(EmployeeServiceCommand command);
    List<EmployeeListDto> getEmployees();
    void addAnEmployee(EmployeeServiceCommand command);
    void deleteAnEmployee(EmployeeServiceCommand command);
}
