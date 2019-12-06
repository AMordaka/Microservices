package pl.dmcs.occupant.service.occupantservice.service.inf;


import pl.dmcs.occupant.service.occupantservice.exception.OccupantNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Occupant;

public interface OccupantService {

    Occupant get(int id) throws OccupantNotFoundException;

}
