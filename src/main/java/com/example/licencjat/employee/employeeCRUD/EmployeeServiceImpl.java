package com.example.licencjat.employee.employeeCRUD;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.employee.employeeCRUD.models.Employee;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeListDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeServiceCommand;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.userData.UserServiceImpl;
import com.example.licencjat.userData.models.UserServiceCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
        var employee = employeeRepository.findById(command.getId()).orElseThrow(IncorrectIdInputException::new);

        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeListDto> getEmployees() {
        var employees = employeeRepository.findAll();

        return employees.stream()
                .map(this::mapEmployeeToEmployeeListDto
                ).collect(Collectors.toList());
    }

    private EmployeeListDto mapEmployeeToEmployeeListDto(Employee e) {
        var mappedEmployee = mapper.map(e, EmployeeListDto.class);
        mappedEmployee.setIsAvailable(true);
        for (Order o :
                e.getOrderList()) {
            if (!o.isDone()) {
                mappedEmployee.setIsAvailable(false);
            }
        }

        return mappedEmployee;
    }

    @Override
    public void addAnEmployee(EmployeeServiceCommand command) {
        var user = userService.addUser(UserServiceCommand.builder().webInput(command.getWebInput()).build());

        var id = UUID.fromString(idGenerator.generateId());

        employeeRepository.save(Employee.builder().id(id).user(user).build());
    }

    @Override
    public void deleteAnEmployee(EmployeeServiceCommand command) {
        employeeRepository.deleteById(command.getId());
    }
}
