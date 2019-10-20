package pl.dmcs.manager.service.managerservice.service.inf;

import pl.dmcs.manager.service.managerservice.exception.BuildingNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Bill;
import pl.dmcs.manager.service.managerservice.model.Premises;

import java.util.List;
import java.util.Set;

public interface PremisesService {

    int save(Premises premises);

    Premises get(int id) throws PremisesNotFoundException;

    int update(Premises premises);

    void delete(int id);

    Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException;

    void setBillAccepted(int billId);

    Set<Premises> getPremisesForSpecificBuidling(int buildingId) throws BuildingNotFoundException;

    Set<Premises> getAllAvailablePremises();

    List<Premises> getAllPremises();
}
