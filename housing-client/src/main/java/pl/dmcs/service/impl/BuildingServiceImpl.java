package pl.dmcs.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.exception.BuildingNotFoundException;
import pl.dmcs.model.Building;
import pl.dmcs.model.Local;
import pl.dmcs.repository.BuildingRepository;
import pl.dmcs.service.BuildingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public void registerBuilding(Building building) {
        buildingRepository.save(building);
    }

    @Override
    public void updateBuilding(Building building) {
        buildingRepository.save(building);
    }

    @Override
    public void removeBuilding(Integer id) {
        buildingRepository.deleteById(id);
    }

    @Override
    public void addLocalToBuilding(Local local, Integer id) {
        Building building = findById(id);
        Set<Local> locals = building.getLocals();
        locals.add(local);
        building.setLocals(locals);
        buildingRepository.save(building);
    }

    @Override
    public Building findById(Integer id) {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional
    public List<Local> getLocalsFromBuilding(Integer id) {
        Set<Local> locals = buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException(String.valueOf(id))).getLocals();
        return new ArrayList<>(locals);
    }
}
