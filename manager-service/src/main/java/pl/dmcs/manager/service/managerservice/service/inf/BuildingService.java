package pl.dmcs.manager.service.managerservice.service.inf;

import pl.dmcs.manager.service.managerservice.exception.BuildingNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Building;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.model.dto.BuildingDto;
import pl.dmcs.manager.service.managerservice.model.dto.PremisesDto;
import pl.dmcs.manager.service.managerservice.model.dto.UpdateBuildingDto;

import java.util.List;
import java.util.Set;

public interface BuildingService {

    int save(BuildingDto building);

    Building get(int id) throws BuildingNotFoundException;

    int update(UpdateBuildingDto buildingDto) throws BuildingNotFoundException;

    void delete(int id) throws BuildingNotFoundException;

    void addPremisesToBuilding(PremisesDto premises, int buildingId);

    void deletePremisesFromBuidling(int premisesId, int buildingId);

    List<Building> getAll();

    Set<Premises> getPremisesForBuilding(int buildingId) throws BuildingNotFoundException;

//    Set<Building> getBuildingsForManageR(int managerId);

//    Set<Building> gettAllAvailableBuildings();
}
