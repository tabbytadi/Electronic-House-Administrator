package org.example.service.contracts;

import org.example.dto.ApartmentDto;
import org.example.entity.Apartment;

import java.math.BigDecimal;
import java.util.List;

public interface ApartmentService {
    void createApartment(ApartmentDto apartmentDto);
    void updateApartment(ApartmentDto apartmentDto);
    void deleteApartment(long apartmentId);
    Apartment getApartmentById(long apartmentId);
    List<Apartment> getAllApartments();

    BigDecimal calculateMonthlyFee(long apartmentId);

    List<ApartmentDto> getApartmentsInBuilding(long buildingId);
}