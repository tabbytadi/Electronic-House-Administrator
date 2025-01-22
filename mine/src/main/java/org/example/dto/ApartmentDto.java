package org.example.dto;

import org.example.entity.PetType; // Import your PetType enum

import java.math.BigDecimal;

public class ApartmentDto {
    private String number;
    private BigDecimal area;
    private long ownerId; // Reference to the owner
    private long buildingId; // Reference to the building
    private boolean hasPet; // Indicates if the apartment has a pet
    private PetType petType; // Type of pet in the apartment

    // Getters and Setters
    public String getNumber() {
        return String.valueOf(Integer.parseInt(number));
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }
}