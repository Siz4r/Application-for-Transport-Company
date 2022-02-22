package com.example.licencjat.employee.employeeCRUD;

import com.example.licencjat.employee.employeeCRUD.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
