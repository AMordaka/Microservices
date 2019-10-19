package pl.dmcs.manager.service.managerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Occupant;
import pl.dmcs.manager.service.managerservice.service.inf.OccupantService;

@RestController
@RequestMapping("/occupant")
public class OccupantController {

    @Autowired
    private OccupantService occupantService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOccupant(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(occupantService.get(id));
        } catch (OccupantNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createOccupant(@RequestBody Occupant occupant) {
        int id = occupantService.save(occupant);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateOccupant(@RequestBody Occupant occupant) {
        int id = occupantService.save(occupant);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteOccupant(@PathVariable("id") int id) throws OccupantNotFoundException {
        occupantService.delete(id);
        return ResponseEntity.ok().build();

    }


    @RequestMapping(value = "/{premisesId}/{occupantId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addPremisesToOccupant(@PathVariable("premisesId") int premisesId, @PathVariable("occupantId") int occupantId) {
        occupantService.addPremisesToOccupant(premisesId,occupantId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{premisesId}/{occupantId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePremisesFromOcuppant(@PathVariable("premisesId") int premisesId, @PathVariable("occupantId") int occupantId) {
        occupantService.deletePremisesFromOccupant(premisesId, occupantId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllOccupants(){
        return ResponseEntity.ok(occupantService.getAll());
    }
}
