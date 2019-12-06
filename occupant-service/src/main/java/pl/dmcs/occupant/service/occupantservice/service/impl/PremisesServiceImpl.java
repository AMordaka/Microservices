package pl.dmcs.occupant.service.occupantservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.occupant.service.occupantservice.exception.OccupantNotFoundException;
import pl.dmcs.occupant.service.occupantservice.exception.PremisesNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Occupant;
import pl.dmcs.occupant.service.occupantservice.model.Premises;
import pl.dmcs.occupant.service.occupantservice.repository.PremisesRepository;
import pl.dmcs.occupant.service.occupantservice.service.inf.OccupantService;
import pl.dmcs.occupant.service.occupantservice.service.inf.PremisesService;

import java.util.Set;

@Service
public class PremisesServiceImpl implements PremisesService {

    @Autowired
    private PremisesRepository premisesRepository;

    @Autowired
    private OccupantService occupantService;

    @Override
    public int save(Premises premises) {
        return premisesRepository.save(premises).getId();
    }

    @Override
    public Premises get(int id) throws PremisesNotFoundException {
        return premisesRepository.findById(id).orElseThrow(()->new PremisesNotFoundException("Premises id:" + id));
    }

    @Override
    public Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException {
        Occupant occupant = occupantService.get(occupantId);
        return occupant.getPremises();
    }


}
