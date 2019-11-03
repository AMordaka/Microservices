package pl.dmcs.manager.service.managerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.UserDetailsNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Occupant;
import pl.dmcs.manager.service.managerservice.model.dto.OccupantDto;
import pl.dmcs.manager.service.managerservice.model.dto.UserDetails;
import pl.dmcs.manager.service.managerservice.service.inf.OccupantService;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/details",method = RequestMethod.POST)
    public ResponseEntity addDetailsToUser(@RequestBody UserDetails userDetails) {
        try {
            occupantService.addUserDetailsToOccupant(userDetails);
            return ResponseEntity.ok().build();
        } catch (OccupantNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @RequestMapping(value = "/{id}/withDetails", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOccupantWithDetails(HttpServletRequest httpServletRequest,@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(occupantService.getWithDetails(id,httpServletRequest.getHeader("Authorization")));
        } catch (OccupantNotFoundException | UserDetailsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createOccupant(@RequestBody OccupantDto occupantDto) {
        int id = occupantService.save(occupantDto);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateOccupant(@RequestBody OccupantDto occupant) {
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
