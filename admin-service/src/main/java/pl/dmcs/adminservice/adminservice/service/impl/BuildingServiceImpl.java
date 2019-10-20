package pl.dmcs.adminservice.adminservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.adminservice.adminservice.exception.BuildingNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Building;
import pl.dmcs.adminservice.adminservice.repository.BuildingRepository;
import pl.dmcs.adminservice.adminservice.service.inf.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

   @Autowired
   private BuildingRepository buildingRepository;

    @Override
    public int save(Building building) {
        return buildingRepository.saveAndFlush(building).getId();
    }

    @Override
    public Building get(int id) throws BuildingNotFoundException {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException("Building id" + id));
    }
}
