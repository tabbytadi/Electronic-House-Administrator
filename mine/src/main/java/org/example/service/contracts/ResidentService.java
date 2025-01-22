package org.example.service.contracts;

import org.example.dto.ResidentDto;
import org.example.entity.Resident;

import java.util.List;

public interface ResidentService {
    void createResident(ResidentDto residentDto);
    void updateResident(ResidentDto residentDto);
    void deleteResident(long residentId);
    Resident getResidentById(long residentId);
    List<Resident> getAllResidents();
}