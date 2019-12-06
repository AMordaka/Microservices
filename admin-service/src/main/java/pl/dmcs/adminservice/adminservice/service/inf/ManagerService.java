package pl.dmcs.adminservice.adminservice.service.inf;


import pl.dmcs.adminservice.adminservice.model.dto.ManagerDto;
import pl.dmcs.adminservice.adminservice.exception.ManagerNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Manager;
import pl.dmcs.adminservice.adminservice.model.dto.UpdateManagerDto;

import java.util.List;

public interface ManagerService {

    int save(ManagerDto managerDto);

    Manager get(int id) throws ManagerNotFoundException;

    int update(UpdateManagerDto managerDto) throws ManagerNotFoundException;

    void delete(int id);

    List<Manager> getAll();

    void addBuildingToManager(int buildingId, int managerId);

    void deleteBuildingFromManager(int buildingId, int managerId);
}
