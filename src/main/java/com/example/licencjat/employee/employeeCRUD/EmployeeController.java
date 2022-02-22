package com.example.licencjat.employee.employeeCRUD;

import com.example.licencjat.employee.employeeCRUD.models.EmployeeDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeListDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeServiceCommand;
import com.example.licencjat.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeListDto> getEmployees() {
        return service.getEmployees();
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeDto getEmployeeById(@PathVariable("id") UUID id) {
        return service.getEmployeeById(EmployeeServiceCommand.builder().id(id).build());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    public void createAnEmployee(@RequestBody UserWebInput webInput) {
        service.addAnEmployee(EmployeeServiceCommand.builder().webInput(webInput).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    public void deleteAnEmployee(@PathVariable("id") UUID id) {
        service.deleteAnEmployee(EmployeeServiceCommand.builder().id(id).build());
    }
}
