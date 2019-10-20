package pl.dmcs.adminservice.adminservice.service.inf;


import pl.dmcs.adminservice.adminservice.exception.OccupantNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Occupant;

public interface OccupantService {
    Occupant get(int id) throws OccupantNotFoundException;

}
