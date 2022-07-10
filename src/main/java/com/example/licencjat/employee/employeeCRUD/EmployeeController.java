package com.example.licencjat.employee.employeeCRUD;

import com.example.licencjat.UI.Annotations.PreAuthorizeAdmin;
import com.example.licencjat.UI.Annotations.PreAuthorizeAdminAndEmployee;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeListDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeServiceCommand;
import com.example.licencjat.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorizeAdminAndEmployee
    public List<EmployeeListDto> getEmployees() {
        return service.getEmployees();
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorizeAdminAndEmployee
    public EmployeeDto getEmployeeById(@PathVariable("id") UUID id) {
        return service.getEmployeeById(EmployeeServiceCommand.builder().id(id).build());
    }

    @PostMapping
    @PreAuthorizeAdmin
    public ResponseEntity<String> createAnEmployee(@RequestBody @Valid UserWebInput webInput) {
        return new ResponseEntity<>(
                service.addAnEmployee(EmployeeServiceCommand.builder().webInput(webInput).build()).toString(),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorizeAdmin
    public void deleteAnEmployee(@PathVariable("id") UUID id) {
        service.deleteAnEmployee(EmployeeServiceCommand.builder().id(id).build());
    }
}
