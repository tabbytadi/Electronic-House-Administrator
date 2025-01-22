package org.example.service.implementations;

import org.example.dao.ApartmentDao;
import org.example.dto.ApartmentDto;
import org.example.entity.Apartment;
import org.example.entity.Building;
import org.example.service.contracts.ApartmentService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentDao apartmentDao;

    public ApartmentServiceImpl(ApartmentDao apartmentDao) {
        this.apartmentDao = apartmentDao;
    }

    @Override
    public void createApartment(ApartmentDto apartmentDto) {
        Apartment apartment = new Apartment();
        apartment.setNumber(Integer.parseInt(String.valueOf(apartmentDto.getNumber())));
        apartment.setArea(apartmentDto.getArea());  // Directly set BigDecimal

        Building building = new Building();
        building.setId(apartmentDto.getBuildingId());
        apartment.setBuilding(building);

        apartment.setHasPet(false); // Default; this can be adjusted if required
        apartmentDao.saveApartment(apartment);
    }

    @Override
    public void updateApartment(ApartmentDto apartmentDto) {
        Apartment apartment = apartmentDao.getApartmentById(apartmentDto.getBuildingId());
        if (apartment != null) {
            apartment.setNumber(Integer.parseInt(apartmentDto.getNumber()));
            apartment.setArea(apartmentDto.getArea());  // Directly set BigDecimal

            Building building = new Building();
            building.setId(apartmentDto.getBuildingId());
            apartment.setBuilding(building);

            apartmentDao.updateApartment(apartment);
        } else {
            throw new IllegalArgumentException("Apartment with ID " + apartmentDto.getBuildingId() + " not found.");
        }
    }

    @Override
    public void deleteApartment(long apartmentId) {
        Apartment apartment = apartmentDao.getApartmentById(apartmentId);
        if (apartment != null) {
            apartmentDao.deleteApartment(apartment);
        } else {
            throw new IllegalArgumentException("Apartment with ID " + apartmentId + " not found.");
        }
    }

    @Override
    public Apartment getApartmentById(long apartmentId) {
        Apartment apartment = apartmentDao.getApartmentById(apartmentId);
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment with ID " + apartmentId + " not found.");
        }
        return apartment;
    }

    @Override
    public List<Apartment> getAllApartments() {
        return apartmentDao.getAllApartments();
    }

    @Override
    public BigDecimal calculateMonthlyFee(long apartmentId) {
        Apartment apartment = apartmentDao.getApartmentById(apartmentId);
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment with ID " + apartmentId + " not found.");
        }

        double areaFee = apartment.getArea().doubleValue();
        double residentFee = apartment.getResidents().stream()
                .filter(resident -> resident.getAge() > 7)
                .mapToDouble(resident -> 5.0)
                .sum();

        double petFee = apartment.isHasPet() ? apartment.getPetType().getMonthlyFee() : 0;

        return BigDecimal.valueOf(areaFee + residentFee + petFee);
    }

    @Override
    public List<ApartmentDto> getApartmentsInBuilding(long buildingId) {
        List<Apartment> apartments = apartmentDao.getAllApartments().stream()
                .filter(apartment -> apartment.getBuilding().getId() == buildingId)
                .collect(Collectors.toList());

        return apartments.stream().map(apartment -> {
            ApartmentDto dto = new ApartmentDto();
            dto.setNumber(String.valueOf(apartment.getNumber()));
            dto.setArea(apartment.getArea());
            dto.setBuildingId(apartment.getBuilding().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}