package pl.dmcs.manager.service.managerservice.service.inf;

import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.UserDetailsNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Occupant;
import pl.dmcs.manager.service.managerservice.model.dto.*;

import java.util.List;

public interface OccupantService  {
    int save(OccupantDto occupant);

    Occupant get(int id) throws OccupantNotFoundException;

    int update(UpdateOccupantDto occupant) throws OccupantNotFoundException;

    void delete(int id) throws OccupantNotFoundException;

    void addPremisesToOccupant(int premisesId, int occupantId);

    void deletePremisesFromOccupant(int premisesId, int occupantId);

    OccupantDetailsDto getWithDetails(int id, String token) throws OccupantNotFoundException, UserDetailsNotFoundException;

    void updateOccupantAccountNumber(int id, AccountNumberDto accountNumberDto);

    List<Occupant> getAll();

    void addUserDetailsToOccupant(UserDetails userDetails) throws OccupantNotFoundException;
}
