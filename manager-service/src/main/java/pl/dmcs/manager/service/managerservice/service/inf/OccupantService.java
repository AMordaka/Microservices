package pl.dmcs.manager.service.managerservice.service.inf;

import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.UserDetailsNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Occupant;
import pl.dmcs.manager.service.managerservice.model.dto.OccupantDetailsDto;
import pl.dmcs.manager.service.managerservice.model.dto.OccupantDto;
import pl.dmcs.manager.service.managerservice.model.dto.UpdateOccupantDto;
import pl.dmcs.manager.service.managerservice.model.dto.UserDetails;

import java.util.List;

public interface OccupantService  {
    int save(OccupantDto occupant);

    Occupant get(int id) throws OccupantNotFoundException;

    int update(UpdateOccupantDto occupant) throws OccupantNotFoundException;

    void delete(int id) throws OccupantNotFoundException;

    void addPremisesToOccupant(int premisesId, int occupantId);

    void deletePremisesFromOccupant(int premisesId, int occupantId);

    OccupantDetailsDto getWithDetails(int id, String token) throws OccupantNotFoundException, UserDetailsNotFoundException;

    List<Occupant> getAll();

    void addUserDetailsToOccupant(UserDetails userDetails) throws OccupantNotFoundException;
}
