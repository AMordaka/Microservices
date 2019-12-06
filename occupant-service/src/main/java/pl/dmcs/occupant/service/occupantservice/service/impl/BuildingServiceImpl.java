package pl.dmcs.occupant.service.occupantservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.occupant.service.occupantservice.exception.BuildingNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Building;
import pl.dmcs.occupant.service.occupantservice.repository.BuildingRepository;
import pl.dmcs.occupant.service.occupantservice.service.inf.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

   @Autowired
   private BuildingRepository buildingRepository;


    @Override
    public Building get(int id) throws BuildingNotFoundException {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException("Building id" + id));
    }



}
