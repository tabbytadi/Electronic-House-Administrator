package org.example.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Employee extends Person {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // Company entity that the employee belongs to

    @OneToMany(mappedBy = "employee")
    private List<Building> assignedBuildings; // Buildings managed by this employee

    @Column(name = "position")
    private String position; // Job position of the employee

    @Column(name = "salary")
    private BigDecimal salary; // Salary of the employee

    // Constructor, getters, and setters
    public Employee() {}

    public Employee(String name, int age, Company company, List<Building> assignedBuildings, String position, BigDecimal salary) {
        super(name, age);
        this.company = company;
        this.assignedBuildings = assignedBuildings;
        this.position = position;
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Building> getAssignedBuildings() {
        return assignedBuildings;
    }

    public void setAssignedBuildings(List<Building> assignedBuildings) {
        this.assignedBuildings = assignedBuildings;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    // Accept `double` for compatibility with `EmployeeDto`
    public void setSalary(double salary) {
        this.salary = BigDecimal.valueOf(salary);
    }
}