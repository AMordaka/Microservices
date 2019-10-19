package pl.dmcs.manager.service.managerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.manager.service.managerservice.exception.BuildingNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Building;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.service.inf.BuildingService;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getBuilding(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(buildingService.get(id));
        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createBuilding(@RequestBody Building building) {
        int id = buildingService.save(building);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateBuilding(@RequestBody Building building) {
        int id = buildingService.save(building);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteBuilding(@PathVariable("id") int id) throws BuildingNotFoundException {
        buildingService.delete(id);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/{buildingId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addPremisesToBuilding(@RequestBody Premises premises, @PathVariable("buildingId") int buildingId) {
        buildingService.addPremisesToBuilding(premises, buildingId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{premisesId}/{buildingId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePremisesFromBuilding(@PathVariable("premisesId") int premisesId, @PathVariable("buildingId") int buildingId) {
        buildingService.deletePremisesFromBuidling(premisesId, buildingId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllBuildings() {
        return ResponseEntity.ok(buildingService.getAll());
    }

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPremisesForBuilding(@PathVariable("id") int buildingId) throws BuildingNotFoundException {
        return ResponseEntity.ok(buildingService.getPremisesForBuilding(buildingId));
    }

}
