package org.example.service.contracts;

import org.example.dto.BuildingDto;

import java.util.List;

public interface BuildingService {
    /**
     * Creates a new building based on the provided BuildingDto.
     *
     * @param buildingDto the DTO containing the building details.
     */
    void createBuilding(BuildingDto buildingDto);

    /**
     * Updates an existing building with new details from the provided BuildingDto.
     *
     * @param buildingDto the DTO containing updated building details.
     */
    void updateBuilding(BuildingDto buildingDto);

    /**
     * Deletes a building by its ID.
     *
     * @param buildingId the ID of the building to be deleted.
     */
    void deleteBuilding(long buildingId);

    /**
     * Retrieves a single building by its ID and maps it to a DTO.
     *
     * @param buildingId the ID of the building to retrieve.
     * @return a BuildingDto containing the building's details.
     */
    BuildingDto getBuildingById(long buildingId);

    /**
     * Retrieves all buildings and maps them to a list of DTOs.
     *
     * @return a list of BuildingDto containing details of all buildings.
     */
    List<BuildingDto> getAllBuildings();
}