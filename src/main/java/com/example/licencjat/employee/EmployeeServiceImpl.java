package com.example.licencjat.employee;

import com.example.licencjat.employee.models.Employee;
import com.example.licencjat.employee.models.EmployeeDto;
import com.example.licencjat.employee.models.EmployeeListDto;
import com.example.licencjat.employee.models.EmployeeServiceCommand;
import com.example.licencjat.exceptions.IncorrectIdInputException;
import com.example.licencjat.user.IdGenerator;
import com.example.licencjat.user.UserServiceImpl;
import com.example.licencjat.user.models.UserServiceCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;
    private final IdGenerator idGenerator;
    private final UserServiceImpl userService;

    @Override
    public EmployeeDto getEmployeeById(EmployeeServiceCommand command) {
        var employee = employeeRepository.findById(command.getId()).orElseThrow(() -> new IncorrectIdInputException(""));

        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeListDto> getEmployees() {
        var employees = employeeRepository.findAll();

        return employees.stream()
                .map(e -> mapper.map(e, EmployeeListDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addAnEmployee(EmployeeServiceCommand command) {
        var user = userService.addUser(UserServiceCommand.builder().webInput(command.getWebInput()).build());

        var id = idGenerator.generateId();

        employeeRepository.save(Employee.builder().id(id).user(user).build());
    }

    @Override
    public void deleteAnEmployee(EmployeeServiceCommand command) {
        employeeRepository.deleteById(command.getId());
    }
}
