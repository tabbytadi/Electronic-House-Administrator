package org.example.entity;

public enum PetType {
    DOG(20),
    CAT(15),
    BIRD(10),
    OTHER(5);

    private final int monthlyFee;

    PetType(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }
}