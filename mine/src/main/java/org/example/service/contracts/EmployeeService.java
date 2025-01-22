package org.example.service.contracts;

import org.example.dto.EmployeeDto;
import org.example.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeDto employeeDto);
    void updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee(long employeeId);
    Employee getEmployeeById(long employeeId);
    List<Employee> getAllEmployees();
}