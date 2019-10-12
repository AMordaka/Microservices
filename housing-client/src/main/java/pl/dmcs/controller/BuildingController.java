package pl.dmcs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.model.Building;
import pl.dmcs.model.Local;
import pl.dmcs.service.BuildingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/building")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Building> listOfBuildings() {
        return buildingService.getAllBuildings();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateBuilding(@Valid Building building) {
        buildingService.updateBuilding(building);
        return "Updated building";
    }

    @RequestMapping(value ="/{id}",method = RequestMethod.DELETE)
    public String deleteBuilding(@PathVariable Integer id) {
        buildingService.removeBuilding(id);
        return "Removed building:" + id;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addBuildingPost(@Valid Building building) {
        buildingService.registerBuilding(building);
        return "REGISTERED";
    }

    @RequestMapping(value = "/{id}}/addLocal", method = RequestMethod.POST)
    public String addLocalToBuilding(@Valid Local local, @PathVariable Integer id) {
        buildingService.addLocalToBuilding(local,id);
        return "Local added to building:" + id;
    }

    @RequestMapping(value = "/{id}/locals", method = RequestMethod.GET)
    public List<Local> getAllLocalsForBuilding(@PathVariable Integer id) {
        return buildingService.getLocalsFromBuilding(id);
    }
}
