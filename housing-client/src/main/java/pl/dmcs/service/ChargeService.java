package pl.dmcs.service;

import pl.dmcs.model.Charge;
import pl.dmcs.model.Local;

import java.util.List;

public interface ChargeService {

    List<Charge> getAllCharges();

    List<Charge> getChargesFromLocal(Local local);

    Charge get(Integer id);

    void save(Charge charge);

    void update(Charge charge);

    void remove(Integer id);
}
