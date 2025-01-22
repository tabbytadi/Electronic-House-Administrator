package org.example.entity;

import javax.persistence.*;

@Entity
public class Resident extends Person {
    @ManyToOne
    private Apartment apartment;

    private boolean usesElevator;

    public Resident() {}

    public Resident(String name, int age, Apartment apartment, boolean usesElevator) {
        super(name, age);
        this.apartment = apartment;
        this.usesElevator = usesElevator;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public boolean isUsesElevator() {
        return usesElevator;
    }

    public void setUsesElevator(boolean usesElevator) {
        this.usesElevator = usesElevator;
    }
}