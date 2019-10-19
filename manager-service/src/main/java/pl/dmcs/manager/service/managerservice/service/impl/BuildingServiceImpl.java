package pl.dmcs.manager.service.managerservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.manager.service.managerservice.exception.BuildingNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Building;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.repository.BuildingRepository;
import pl.dmcs.manager.service.managerservice.service.inf.BuildingService;
import pl.dmcs.manager.service.managerservice.service.inf.PremisesService;

import java.util.List;
import java.util.Set;

@Service
public class BuildingServiceImpl implements BuildingService {

   @Autowired
   private BuildingRepository buildingRepository;

   @Autowired
   private PremisesService premisesService;



    @Override
    public int save(Building building) {
        return buildingRepository.saveAndFlush(building).getId();
    }

    @Override
    public Building get(int id) throws BuildingNotFoundException {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException("Building id" + id));
    }

    @Override
    public int update(Building building) {
        return buildingRepository.saveAndFlush(building).getId();
    }

    @Override
    public void delete(int id) throws BuildingNotFoundException {
        Building building = null;
        try {
            building = get(id);
        } catch (BuildingNotFoundException e) {
            throw new BuildingNotFoundException("Building not found" + id);
        }

        Set<Premises> premisesSet = building.getPremises();

        for (Premises premises : premisesSet) {
            premises.setOccupant(null);
        }

        building.setPremises(premisesSet);
        building.setManager(null);
        buildingRepository.save(building);
        buildingRepository.deleteById(id);
    }

    @Override
    public void addPremisesToBuilding(Premises premises, int buildingId) {
        Building building = null;

        try {
            building = get(buildingId);
        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }


        if (building != null && premises != null) {

            Set<Premises> premisesSet = building.getPremises();
            premisesSet.add(premises);
            premises.setBuilding(building);
            building.setPremises(premisesSet);
            save(building);
        }
    }

    @Override
    public void deletePremisesFromBuidling(int premisesId, int buildingId) {
        Building building = null;

        try {
            building = get(buildingId);
        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }

        Set<Premises> premisesSet = building.getPremises();

        premisesSet.removeIf(premises -> premises.getId() == premisesId);

        building.setPremises(premisesSet);

        Premises premises = null;
        try {
            premises = premisesService.get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }


        premises.setOccupant(null);
        premises.setBuilding(null);

        premisesService.save(premises);

        premisesService.delete(premises.getId());

        buildingRepository.save(building);
    }

    @Override
    public List<Building> getAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Set<Premises> getPremisesForBuilding(int buildingId) throws BuildingNotFoundException {
        Building building = get(buildingId);

        return building.getPremises();
    }


}
