package pl.dmcs.service.impl;

import org.springframework.stereotype.Service;
import pl.dmcs.exception.ChargeNotFoundException;
import pl.dmcs.model.Charge;
import pl.dmcs.model.Local;
import pl.dmcs.repository.ChargeRepository;
import pl.dmcs.service.ChargeService;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    private final ChargeRepository chargeRepository;

    public ChargeServiceImpl(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Override
    public List<Charge> getAllCharges() {
        return chargeRepository.findAll();
    }

    @Override
    public List<Charge> getChargesFromLocal(Local local) {
        return chargeRepository.getAllByLocal(local).orElseThrow(() -> new ChargeNotFoundException(""));
    }

    @Override
    public Charge get(Integer id) {
        return chargeRepository.getOne(id);
    }

    @Override
    public void save(Charge charge) {
        chargeRepository.save(charge);
    }

    @Override
    public void update(Charge charge) {
        save(charge);
    }

    @Override
    public void remove(Integer id) {
        chargeRepository.deleteById(id);
    }
}
