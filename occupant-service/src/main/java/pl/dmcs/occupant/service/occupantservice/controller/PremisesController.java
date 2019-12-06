package pl.dmcs.occupant.service.occupantservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.occupant.service.occupantservice.exception.OccupantNotFoundException;
import pl.dmcs.occupant.service.occupantservice.service.inf.PremisesService;

@RestController
@RequestMapping("/premises")
public class PremisesController {
    @Autowired
    private PremisesService premisesService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPremisesForOccupant(@PathVariable("id") int occupantId) throws OccupantNotFoundException {
        return ResponseEntity.ok(premisesService.getPremisesForSpecificOccupant(occupantId));
    }
}
