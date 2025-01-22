package org.example.service.implementations;

import org.example.dao.BuildingDao;
import org.example.dto.BuildingDto;
import org.example.entity.Building;
import org.example.service.contracts.BuildingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingServiceImpl implements BuildingService {
    private final BuildingDao buildingDao;

    public BuildingServiceImpl(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @Override
    public void createBuilding(BuildingDto buildingDto) {
        Building building = new Building();
        building.setAddress(buildingDto.getAddress());
        building.setFloors(buildingDto.getNumberOfFloors());
        building.setTotalArea(buildingDto.getTotalArea().doubleValue());
        buildingDao.save(building);
    }

    @Override
    public void updateBuilding(BuildingDto buildingDto) {
        Building building = buildingDao.getBuildingById(buildingDto.getId());
        if (building != null) {
            building.setAddress(buildingDto.getAddress());
            building.setFloors(buildingDto.getNumberOfFloors());
            building.setTotalArea(buildingDto.getTotalArea().doubleValue());
            buildingDao.update(building);
        } else {
            throw new IllegalArgumentException("Building with ID " + buildingDto.getId() + " not found.");
        }
    }

    @Override
    public void deleteBuilding(long buildingId) {
        buildingDao.deleteById(buildingId);
    }

    @Override
    public BuildingDto getBuildingById(long buildingId) {
        Building building = buildingDao.getBuildingById(buildingId);
        if (building != null) {
            BuildingDto dto = new BuildingDto();
            dto.setAddress(building.getAddress());
            dto.setNumberOfFloors(building.getFloors());
            dto.setTotalArea(BigDecimal.valueOf(building.getTotalArea()));
            dto.setNumberOfApartments(building.getApartments() != null ? building.getApartments().size() : 0);
            dto.setSharedArea(BigDecimal.ZERO);
            return dto;
        }
        throw new IllegalArgumentException("Building with ID " + buildingId + " not found.");
    }

    @Override
    public List<BuildingDto> getAllBuildings() {
        return buildingDao.getAll().stream().map(building -> {
            BuildingDto dto = new BuildingDto();
            dto.setAddress(building.getAddress());
            dto.setNumberOfFloors(building.getFloors());
            dto.setTotalArea(BigDecimal.valueOf(building.getTotalArea()));
            dto.setNumberOfApartments(building.getApartments() != null ? building.getApartments().size() : 0);
            dto.setSharedArea(BigDecimal.ZERO);
            return dto;
        }).collect(Collectors.toList());
    }
}