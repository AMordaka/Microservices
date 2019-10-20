package pl.dmcs.manager.service.managerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Bill;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.service.inf.BillService;
import pl.dmcs.manager.service.managerservice.service.inf.PremisesService;

@RestController
@RequestMapping("/premises")
public class PremisesController {

    @Autowired
    private PremisesService premisesService;

    @Autowired
    private BillService billService;



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPremises(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(premisesService.get(id));
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createPremises(@RequestBody Premises premises){
        int id = premisesService.save(premises);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updatePremises(@RequestBody Premises premises) {
        int id = premisesService.save(premises);
        return ResponseEntity.ok(id);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePremises(@PathVariable int id) {
        premisesService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/occupant/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPremisesForOccupant(@PathVariable("id") int occupantId) throws OccupantNotFoundException {
        return ResponseEntity.ok(premisesService.getPremisesForSpecificOccupant(occupantId));
    }

    @RequestMapping(value = "/bills/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getBillsForPremises(@PathVariable("id") int premisesId) throws PremisesNotFoundException {
        return ResponseEntity.ok(billService.getBillsForSpecificPremises(premisesId));
    }

    @RequestMapping(value = "/bill/accept/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity setBillAccepted(@PathVariable("id") int billId) {
        premisesService.setBillAccepted(billId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{billId}/{premisesId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteBillFromPremises(@PathVariable("billId") int billId, @PathVariable("premisesId") int premisesId) {
        billService.deleteBillFromPremises(billId, premisesId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllAvailablePremies(){
        return ResponseEntity.ok(premisesService.getAllAvailablePremises());
    }
}
