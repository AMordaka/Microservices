package pl.dmcs.occupant.service.occupantservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.occupant.service.occupantservice.model.Bill;
import pl.dmcs.occupant.service.occupantservice.service.inf.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addBillToPremises(@RequestBody Bill bill, @PathVariable("id") int id) {
        billService.addBillToPremises(bill,id);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/pdf/{id}/{lang}", method = RequestMethod.GET,produces = "application/pdf")
    @ResponseBody
    public ResponseEntity getPdf(@PathVariable("id") int id, @PathVariable("lang") String lang) {
        if (billService.getPdfForBillId(id,lang) ==null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(billService.getPdfForBillId(id,lang));
        }
    }
}
