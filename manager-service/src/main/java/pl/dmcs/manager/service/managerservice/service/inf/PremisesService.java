package pl.dmcs.manager.service.managerservice.service.inf;

import pl.dmcs.manager.service.managerservice.exception.BuildingNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Bill;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.model.dto.PremisesDto;
import pl.dmcs.manager.service.managerservice.model.dto.UpdatePremisesDto;

import java.util.List;
import java.util.Set;

public interface PremisesService {

    int save(Premises premises);

    int saveDto(PremisesDto premisesDto);

    Premises get(int id) throws PremisesNotFoundException;

    int update(UpdatePremisesDto premises);

    void delete(int id);

    Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException;

    void setBillAccepted(int billId);

    Set<Premises> getPremisesForSpecificBuidling(int buildingId) throws BuildingNotFoundException;

    Set<Premises> getAllAvailablePremises();

    List<Premises> getAllPremises();
}
