package pl.dmcs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.model.Charge;
import pl.dmcs.service.ChargeService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ChargeController {

    private ChargeService chargeService;

    public ChargeController(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    @GetMapping(value = "/charges")
    public List<Charge> listCharges() {
        return chargeService.getAllCharges();
    }


    @PostMapping(value = "/charge")
    public void addBuildingPost(@Valid Charge charge) {
        chargeService.save(charge);
    }
}
