package com.example.licencjat.employee.employeeOrder;

import com.example.licencjat.employee.employeeCRUD.EmployeeRepository;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.orders.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class EmployeeOrderServiceImpl implements EmployeeOrderService{
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;

    @Override
    public void assignEmployeeToOrder(UUID employeeId, UUID orderId) {
        var employee = employeeRepository.findById(employeeId).orElseThrow(IncorrectIdInputException::new);
        var order = orderRepository.findById(orderId).orElseThrow(IncorrectIdInputException::new);
        employee.addOrder(order);

        employeeRepository.save(employee);
    }

    @Override
    public void assignBackEmployeeFromOrder(UUID employeeId, UUID orderId) {
        var employee = employeeRepository.findById(employeeId).orElseThrow(IncorrectIdInputException::new);
        var order = orderRepository.findById(orderId).orElseThrow(IncorrectIdInputException::new);

        employee.deleteOrder(order);

        employeeRepository.save(employee);
    }
}
