package pl.dmcs.adminservice.adminservice.service.impl;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmcs.adminservice.adminservice.model.Role;
import pl.dmcs.adminservice.adminservice.model.dto.ManagerDto;
import pl.dmcs.adminservice.adminservice.exception.BuildingNotFoundException;
import pl.dmcs.adminservice.adminservice.exception.ManagerNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Building;
import pl.dmcs.adminservice.adminservice.model.Manager;
import pl.dmcs.adminservice.adminservice.model.User;
import pl.dmcs.adminservice.adminservice.model.dto.UpdateManagerDto;
import pl.dmcs.adminservice.adminservice.repository.ManagerRepository;
import pl.dmcs.adminservice.adminservice.service.inf.BuildingService;
import pl.dmcs.adminservice.adminservice.service.inf.ManagerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int save(ManagerDto managerDto) {
        Manager manager = new Manager();
        User user = new User();
        user.setName(managerDto.getFirstName());
        user.setLastName(managerDto.getLastName());
        user.setEmail(managerDto.getEmail());
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("ROLE_MANAGER");
        roles.add(role);
        user.setRoles(roles);
        user.setActive(managerDto.getActive());
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        manager.setUser(user);
        return managerRepository.saveAndFlush(manager).getId();
    }

    @Override
    public Manager get(int id) throws ManagerNotFoundException {
      return  managerRepository.findById(id).orElseThrow(()->new ManagerNotFoundException("Manager id" +id));
    }

    @Override
    public int update(UpdateManagerDto managerDto) throws ManagerNotFoundException {
        Manager manager = get(managerDto.getId());
        User user  = manager.getUser();
        user.setName(managerDto.getFirstName());
        user.setLastName(managerDto.getLastName());
        user.setEmail(managerDto.getEmail());
        user.setActive(managerDto.getActive());
        manager.setUser(user);

        return managerRepository.saveAndFlush(manager).getId();
    }

    @Override
    public void delete(int id) {
        Manager manager = null;

        try {
            manager = get(id);
        } catch (ManagerNotFoundException e) {
            e.printStackTrace();
        }

        Set<Building> buildings = manager.getBuildings();

        for (Building building : buildings) {
            building.setManager(null);
        }

        manager.setBuildings(buildings);
        managerRepository.save(manager);
        managerRepository.deleteById(id);
    }

    @Override
    public List<Manager> getAll() {
       return managerRepository.findAll();
    }

    @Override
    public void addBuildingToManager(int buildingId, int managerId) {
        Building building = null;
        Manager manager = null;

        try {
            building = buildingService.get(buildingId);
        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }

        try {
            manager = get(managerId);
        } catch (ManagerNotFoundException e) {
            e.printStackTrace();
        }

        if (building != null && manager != null && building.getManager() == null) {
            Set<Building> buildingSet = manager.getBuildings();
            buildingSet.add(building);
            building.setManager(manager);
            buildingService.save(building);
            manager.setBuildings(buildingSet);
            managerRepository.save(manager);
        }
    }

    @Override
    public void deleteBuildingFromManager(int buildingId, int managerId) {
        Manager manager = null;
        Building building = null;
        try {
            manager = get(managerId);
        } catch (ManagerNotFoundException e) {
            e.printStackTrace();
        }

        try {
            building = buildingService.get(buildingId);
        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }

        Set<Building> buildings = manager.getBuildings();

        buildings.removeIf(b -> b.getId() == buildingId);
        manager.setBuildings(buildings);
        managerRepository.save(manager);

        building.setManager(null);
        buildingService.save(building);

    }
}
