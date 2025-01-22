package org.example;

import org.example.dao.*;
import org.example.dto.*;
import org.example.entity.*;
import org.example.service.contracts.*;
import org.example.service.implementations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Use the concrete DAO implementations
        ApartmentDao apartmentDao = new ApartmentDao();
        BuildingDao buildingDao = new BuildingDao();
        CompanyDao companyDao = new CompanyDao();
        EmployeeDao employeeDao = new EmployeeDao();
        ResidentDao residentDao = new ResidentDao();

        // Service instances using the correct DAO implementations
        ApartmentService apartmentService = new ApartmentServiceImpl(apartmentDao);
        BuildingService buildingService = new BuildingServiceImpl(buildingDao);
        CompanyService companyService = new CompanyServiceImpl(companyDao, employeeDao);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDao, buildingDao, companyDao);
        ResidentService residentService = new ResidentServiceImpl(residentDao, apartmentDao);

        try {
            // Create a building
            BuildingDto buildingDto = new BuildingDto();
            buildingDto.setAddress("123 Main St");
            buildingDto.setNumberOfFloors(5);
            buildingDto.setTotalArea(BigDecimal.valueOf(5000));
            buildingService.createBuilding(buildingDto);

            // Create an apartment
            ApartmentDto apartmentDto = new ApartmentDto();
            apartmentDto.setNumber("101");
            apartmentDto.setArea(BigDecimal.valueOf(100));
            apartmentDto.setBuildingId(1L);
            apartmentDto.setHasPet(true);
            apartmentDto.setPetType(PetType.DOG);
            apartmentService.createApartment(apartmentDto);
            // Calculate monthly fee for apartment
            BigDecimal monthlyFee = apartmentService.calculateMonthlyFee(1L);
            System.out.println("Monthly fee for apartment 101: " + monthlyFee);
            // Retrieve and print all apartments in the building
            List<ApartmentDto> apartmentsInBuilding = apartmentService.getApartmentsInBuilding(1L);
            System.out.println("Apartments in building:");
            apartmentsInBuilding.forEach(apartment -> System.out.println("Apartment number: " + apartment.getNumber() + ", Area: " + apartment.getArea()));

            // Create a company
            CompanyDto companyDto = new CompanyDto();
            companyDto.setName("Real Estate Co.");
            companyDto.setAddress("123 Business Street");
            companyDto.setFoundationDate(new Date());
            companyDto.setEmployeeIds(List.of(1L, 2L));
            companyService.createCompany(companyDto);

            // Create an employee and assign them to the company and building
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setName("John Doe");
            employeeDto.setPosition("Manager");
            employeeDto.setSalary(BigDecimal.valueOf(5000));
            employeeDto.setCompanyId(1L);
            employeeDto.setBuildingIds(List.of(1L));
            employeeService.createEmployee(employeeDto);

            // Create a resident
            ResidentDto residentDto = new ResidentDto();
            residentDto.setName("Jane Smith");
            residentDto.setAge(30);
            residentDto.setApartmentId(1L);  // Assign resident to apartment with ID 1
            residentDto.setUsesElevator(true);
            residentService.createResident(residentDto);

            // Calculate monthly fee for apartment
//            BigDecimal monthlyFee = apartmentService.calculateMonthlyFee(1L);
//            System.out.println("Monthly fee for apartment 101: " + monthlyFee);

            // Update an apartment's area
            apartmentDto.setArea(BigDecimal.valueOf(120));
            apartmentService.updateApartment(apartmentDto);
            Apartment updatedApartment = apartmentService.getApartmentById(1L);
            System.out.println("Updated apartment 101 area: " + updatedApartment.getArea());

            // Delete a resident
            residentService.deleteResident(1L);
            System.out.println("Resident 1 deleted.");

            // Delete an apartment
            apartmentService.deleteApartment(1L);
            System.out.println("Apartment 1 deleted.");

            // Delete a building
            buildingService.deleteBuilding(1L);
            System.out.println("Building 1 deleted.");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}