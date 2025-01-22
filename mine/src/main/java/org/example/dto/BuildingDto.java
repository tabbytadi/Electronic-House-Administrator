package org.example.dto;

import java.math.BigDecimal;

public class BuildingDto {
    private long id; // Add this property
    private String address;
    private int numberOfFloors;
    private int numberOfApartments;
    private BigDecimal totalArea;
    private BigDecimal sharedArea;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getNumberOfApartments() {
        return numberOfApartments;
    }

    public void setNumberOfApartments(int numberOfApartments) {
        this.numberOfApartments = numberOfApartments;
    }

    public BigDecimal getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(BigDecimal totalArea) {
        this.totalArea = totalArea;
    }

    public BigDecimal getSharedArea() {
        return sharedArea;
    }

    public void setSharedArea(BigDecimal sharedArea) {
        this.sharedArea = sharedArea;
    }
}