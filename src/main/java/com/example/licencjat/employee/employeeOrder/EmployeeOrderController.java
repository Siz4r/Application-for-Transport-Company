package com.example.licencjat.employee.employeeOrder;

import com.example.licencjat.UI.Annotations.PreAuthorizeAdmin;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees/{employeeId}/orders/{orderId}")
public class EmployeeOrderController {
    private final EmployeeOrderService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    @PreAuthorizeAdmin
    public void assingEmployeeToOrder(@PathVariable("employeeId") UUID employeeId,
                                      @PathVariable("orderId") UUID orderId) {
        service.assignEmployeeToOrder(employeeId, orderId);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorizeAdmin
    public void assignBackEmployeeFromOrder(@PathVariable("employeeId") UUID employeeId,
                                            @PathVariable("orderId") UUID orderId) {
        service.assignBackEmployeeFromOrder(employeeId, orderId);
    }
}
