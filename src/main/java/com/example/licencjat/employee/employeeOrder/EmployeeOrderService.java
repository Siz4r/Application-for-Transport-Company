package com.example.licencjat.employee.employeeOrder;

import java.util.UUID;

interface EmployeeOrderService {
    void assignBackEmployeeFromOrder(UUID employeeId, UUID orderId);

    void assignEmployeeToOrder(UUID employeeId, UUID orderId);
}
