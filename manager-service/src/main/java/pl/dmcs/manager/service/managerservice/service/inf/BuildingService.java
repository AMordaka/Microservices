package pl.dmcs.manager.service.managerservice.service.inf;

import pl.dmcs.manager.service.managerservice.exception.BuildingNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Building;
import pl.dmcs.manager.service.managerservice.model.Premises;

import java.util.List;
import java.util.Set;

public interface BuildingService {

    int save(Building building);

    Building get(int id) throws BuildingNotFoundException;

    int update(Building building);

    void delete(int id) throws BuildingNotFoundException;

    void addPremisesToBuilding(Premises premises, int buildingId);

    void deletePremisesFromBuidling(int premisesId, int buildingId);

    List<Building> getAll();

    Set<Premises> getPremisesForBuilding(int buildingId) throws BuildingNotFoundException;

//    Set<Building> getBuildingsForManageR(int managerId);

//    Set<Building> gettAllAvailableBuildings();
}
