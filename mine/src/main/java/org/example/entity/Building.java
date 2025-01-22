package org.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Building extends BaseEntity {
    private String address;
    private int floors;
    private double totalArea;

    @OneToMany(mappedBy = "building")
    private List<Apartment> apartments;

    @ManyToOne
    private Employee employee;

    public Building() {}

    public Building(String address, int floors, double totalArea, List<Apartment> apartments, Employee employee) {
        this.address = address;
        this.floors = floors;
        this.totalArea = totalArea;
        this.apartments = apartments;
        this.employee = employee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}


//package org.example.entity;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Building extends BaseEntity {
//    private String address;
//    private int floors;
//    private double totalArea;
//
//    @OneToMany(mappedBy = "building")
//    private List<Apartment> apartments;
//
//    @ManyToOne
//    private Employee employee;
//
//    public Building() {}
//
//    public Building(String address, int floors, double totalArea, List<Apartment> apartments, Employee employee) {
//        this.address = address;
//        this.floors = floors;
//        this.totalArea = totalArea;
//        this.apartments = apartments;
//        this.employee = employee;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public int getFloors() {
//        return floors;
//    }
//
//    public void setFloors(int floors) {
//        this.floors = floors;
//    }
//
//    public double getTotalArea() {
//        return totalArea;
//    }
//
//    public void setTotalArea(double totalArea) {
//        this.totalArea = totalArea;
//    }
//
//    public List<Apartment> getApartments() {
//        return apartments;
//    }
//
//    public void setApartments(List<Apartment> apartments) {
//        this.apartments = apartments;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//}