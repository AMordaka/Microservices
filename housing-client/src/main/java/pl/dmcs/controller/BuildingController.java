package pl.dmcs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.model.Building;
import pl.dmcs.service.BuildingService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @RequestMapping(value = "/buildings", method = RequestMethod.GET)
    public List<Building> listOfUsers() {
        return buildingService.getAllBuildings();
    }


    @RequestMapping(value = "/addbuilding", method = RequestMethod.POST)
    public String addBuildingPost(@Valid Building building) {
        buildingService.registerBuilding(building);
        return "REGISTERED";
    }
}
