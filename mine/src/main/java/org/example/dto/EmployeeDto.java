package org.example.dto;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeDto {
    private String name;
    private String position;
    private double salary; // Keeping double for input simplicity
    private long companyId; // Reference to the Company
    private List<Long> buildingIds; // List of Building IDs managed by the employee

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public List<Long> getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(List<Long> buildingIds) {
        this.buildingIds = buildingIds;
    }

    public void setSalary(BigDecimal bigDecimal) { this.salary = bigDecimal.doubleValue();}
}