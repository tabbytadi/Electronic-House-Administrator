package org.example.service.implementations;

import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDao;
import org.example.dao.BuildingDao;
import org.example.dto.EmployeeDto;
import org.example.entity.Employee;
import org.example.entity.Building;
import org.example.entity.Company;
import org.example.service.contracts.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final BuildingDao buildingDao;
    private final CompanyDao companyDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao, BuildingDao buildingDao, CompanyDao companyDao) {
        this.employeeDao = employeeDao;
        this.buildingDao = buildingDao;
        this.companyDao = companyDao;
    }

    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        Company company = companyDao.getCompanyById(employeeDto.getCompanyId());
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + employeeDto.getCompanyId() + " not found.");
        }

        // Create a new Employee
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setPosition(employeeDto.getPosition());
        employee.setSalary(employeeDto.getSalary());
        employee.setCompany(company);

        // Set the buildings assigned to the employee
        List<Building> buildings = employeeDto.getBuildingIds().stream()
                .map(buildingId -> buildingDao.getBuildingById(buildingId))
                .collect(Collectors.toList());

        employee.setAssignedBuildings(buildings);

        // Save the new employee
        employeeDao.saveEmployee(employee);
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        // Fetch the existing employee
        Employee employee = employeeDao.getEmployeeById(employeeDto.getCompanyId());
        if (employee != null) {
            // Update employee details
            employee.setName(employeeDto.getName());
            employee.setPosition(employeeDto.getPosition());
            employee.setSalary(employeeDto.getSalary());

            // Update the company (assuming company can be changed)
            Company company = companyDao.getCompanyById(employeeDto.getCompanyId());
            if (company != null) {
                employee.setCompany(company);
            }

            // Set the new list of buildings
            List<Building> buildings = employeeDto.getBuildingIds().stream()
                    .map(buildingId -> buildingDao.getBuildingById(buildingId))
                    .collect(Collectors.toList());

            employee.setAssignedBuildings(buildings);
            employeeDao.updateEmployee(employee);
        } else {
            throw new IllegalArgumentException("Employee with ID " + employeeDto.getCompanyId() + " not found.");
        }
    }

    @Override
    public void deleteEmployee(long employeeId) {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        if (employee != null) {
            employeeDao.deleteEmployee(employee);
        } else {
            throw new IllegalArgumentException("Employee with ID " + employeeId + " not found.");
        }
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee with ID " + employeeId + " not found.");
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}