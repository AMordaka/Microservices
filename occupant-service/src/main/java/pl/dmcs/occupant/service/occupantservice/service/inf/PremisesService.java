package pl.dmcs.occupant.service.occupantservice.service.inf;


import pl.dmcs.occupant.service.occupantservice.exception.OccupantNotFoundException;
import pl.dmcs.occupant.service.occupantservice.exception.PremisesNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Premises;

import java.util.Set;

public interface PremisesService {

    int save(Premises premises);

    Premises get(int id) throws PremisesNotFoundException;

    Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException;


}
