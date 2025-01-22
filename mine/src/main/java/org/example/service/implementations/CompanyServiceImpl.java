package org.example.service.implementations;

import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDao;
import org.example.dto.CompanyDto;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.service.contracts.CompanyService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;
    private final EmployeeDao employeeDao;

    // Constructor that accepts DAO implementations
    public CompanyServiceImpl(CompanyDao companyDao, EmployeeDao employeeDao) {
        this.companyDao = companyDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public void createCompany(CompanyDto companyDto) {
        // Create a new Company entity from DTO
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());

        // Ensure foundationDate is not null before converting
        if (companyDto.getFoundationDate() != null) {
            company.setFoundationDate(companyDto.getFoundationDate()
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate());
        } else {
            company.setFoundationDate(null);
        }

        // Fetch employees by IDs and associate them with the company
        Set<Employee> employees = companyDto.getEmployeeIds().stream()
                .map(employeeId -> {
                    Employee employee = employeeDao.getEmployeeById(employeeId);
                    if (employee == null) {
                        throw new IllegalArgumentException("Employee with ID " + employeeId + " not found.");
                    }
                    return employee;
                })
                .collect(Collectors.toSet());
        company.setEmployees(employees);

        // Save the new company
        companyDao.saveCompany(company);
    }

    @Override
    public void updateCompany(CompanyDto companyDto) {
        Company company = companyDao.getCompanyById(companyDto.getCompanyId());
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyDto.getCompanyId() + " not found.");
        }

        // Update company details
        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());

        // Ensure foundationDate is not null before converting
        if (companyDto.getFoundationDate() != null) {
            company.setFoundationDate(companyDto.getFoundationDate()
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate());
        } else {
            company.setFoundationDate(null);
        }

        // Fetch employees by IDs and update associations
        Set<Employee> employees = companyDto.getEmployeeIds().stream()
                .map(employeeId -> {
                    Employee employee = employeeDao.getEmployeeById(employeeId);
                    if (employee == null) {
                        throw new IllegalArgumentException("Employee with ID " + employeeId + " not found.");
                    }
                    return employee;
                })
                .collect(Collectors.toSet());
        company.setEmployees(employees);

        // Save the updated company
        companyDao.updateCompany(company);
    }

    @Override
    public void deleteCompany(long companyId) {
        Company company = companyDao.getCompanyById(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " not found.");
        }

        companyDao.deleteCompany(company);
    }

    @Override
    public Company getCompanyById(long companyId) {
        Company company = companyDao.getCompanyById(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " not found.");
        }
        return company;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDao.getAllCompanies();
    }
}