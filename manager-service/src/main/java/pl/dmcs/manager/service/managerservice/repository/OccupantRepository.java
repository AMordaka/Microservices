package pl.dmcs.manager.service.managerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmcs.manager.service.managerservice.model.Occupant;

public interface OccupantRepository extends JpaRepository<Occupant,Integer> {
}
