package com.example.licencjat.employee.employeeOrder;

import com.example.licencjat.employee.employeeCRUD.EmployeeRepository;
import com.example.licencjat.exceptions.IncorrectIdInputException;
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
    public void assingEmployeeToOrder(UUID employeeId, UUID orderId) {
        var employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IncorrectIdInputException(""));
        var order = orderRepository.findById(orderId).orElseThrow(() -> new IncorrectIdInputException(""));
        employee.addOrder(order);

        employeeRepository.save(employee);
    }

    @Override
    public void assignBackEmployeeFromOrder(UUID employeeId, UUID orderId) {
        var employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IncorrectIdInputException(""));
        var order = orderRepository.findById(orderId).orElseThrow(() -> new IncorrectIdInputException(""));

        employee.deleteOrder(order);

        employeeRepository.save(employee);
    }
}
