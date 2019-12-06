package pl.dmcs.occupant.service.occupantservice.service.inf;


import pl.dmcs.occupant.service.occupantservice.exception.BuildingNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Building;

public interface BuildingService {
    Building get(int id) throws BuildingNotFoundException;
}
