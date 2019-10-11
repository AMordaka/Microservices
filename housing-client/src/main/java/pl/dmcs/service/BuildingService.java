package pl.dmcs.service;

import pl.dmcs.model.Building;
import pl.dmcs.model.Local;

import java.util.List;

public interface BuildingService {

    List<Building> getAllBuildings();

    void registerBuilding(Building building);

    Building findById(Integer id);

    List<Local> getLocalsFromBuilding(Integer id);
}
