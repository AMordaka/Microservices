package pl.dmcs.adminservice.adminservice.service.inf;


import pl.dmcs.adminservice.adminservice.exception.BuildingNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Building;

public interface BuildingService {
    int save(Building building);
    Building get(int id) throws BuildingNotFoundException;
}
