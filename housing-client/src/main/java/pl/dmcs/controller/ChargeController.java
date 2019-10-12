package pl.dmcs.controller;

import org.springframework.web.bind.annotation.*;
import pl.dmcs.model.Charge;
import pl.dmcs.service.ChargeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/charge")
public class ChargeController {

    private ChargeService chargeService;

    public ChargeController(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    @GetMapping(value = "/all")
    public List<Charge> listCharges() {
        return chargeService.getAllCharges();
    }


    @PostMapping
    public void addCharge(@Valid Charge charge) {
        chargeService.save(charge);
    }

    @GetMapping(value = "/{id}")
    public Charge getCharge(@PathVariable  Integer id) {
        return chargeService.get(id);
    }

    @PutMapping
    public void updateCharge(@Valid Charge charge) {
        chargeService.update(charge);
    }
}
