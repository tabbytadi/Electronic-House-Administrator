package org.example.service.implementations;

import org.example.dao.ApartmentDao;
import org.example.dao.ResidentDao;
import org.example.dto.ResidentDto;
import org.example.entity.Resident;
import org.example.entity.Apartment;
import org.example.service.contracts.ResidentService;

import java.util.List;

public class ResidentServiceImpl implements ResidentService {

    private final ResidentDao residentDao;
    private final ApartmentDao apartmentDao;

    public ResidentServiceImpl(ResidentDao residentDao, ApartmentDao apartmentDao) {
        this.residentDao = residentDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    public void createResident(ResidentDto residentDto) {
        // Fetch the apartment associated with the resident
        Apartment apartment = apartmentDao.getApartmentById(residentDto.getApartmentId());
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment with ID " + residentDto.getApartmentId() + " not found.");
        }

        // Create a new Resident entity
        Resident resident = new Resident();
        resident.setName(residentDto.getName());
        resident.setAge(residentDto.getAge());
        resident.setApartment(apartment);
        resident.setUsesElevator(residentDto.isUsesElevator());

        // Save the resident to the database
        residentDao.saveResident(resident);
    }

    @Override
    public void updateResident(ResidentDto residentDto) {
        // Fetch the existing resident
        Resident resident = residentDao.getResidentById(residentDto.getApartmentId());
        if (resident != null) {
            // Update resident information
            resident.setName(residentDto.getName());
            resident.setAge(residentDto.getAge());

            // Fetch and update apartment if necessary
            Apartment apartment = apartmentDao.getApartmentById(residentDto.getApartmentId());
            if (apartment != null) {
                resident.setApartment(apartment);
            }

            // Update the elevator usage preference
            resident.setUsesElevator(residentDto.isUsesElevator());

            // Save the updated resident to the database
            residentDao.updateResident(resident);
        } else {
            throw new IllegalArgumentException("Resident with ID " + residentDto.getApartmentId() + " not found.");
        }
    }

    @Override
    public void deleteResident(long residentId) {
        Resident resident = residentDao.getResidentById(residentId);
        if (resident != null) {
            residentDao.deleteResident(resident);
        } else {
            throw new IllegalArgumentException("Resident with ID " + residentId + " not found.");
        }
    }

    @Override
    public Resident getResidentById(long residentId) {
        Resident resident = residentDao.getResidentById(residentId);
        if (resident == null) {
            throw new IllegalArgumentException("Resident with ID " + residentId + " not found.");
        }
        return resident;
    }

    @Override
    public List<Resident> getAllResidents() {
        return residentDao.getAllResidents();
    }
}