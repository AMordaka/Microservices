package pl.dmcs.adminservice.adminservice.service.inf;


import pl.dmcs.adminservice.adminservice.exception.ManagerNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Manager;

import java.util.List;

public interface ManagerService {

    int save(Manager manager);

    Manager get(int id) throws ManagerNotFoundException;

    int update(Manager manager);

    void delete(int id);

    List<Manager> getAll();

    void addBuildingToManager(int buildingId, int managerId);

    void deleteBuildingFromManager(int buildingId, int managerId);
}
