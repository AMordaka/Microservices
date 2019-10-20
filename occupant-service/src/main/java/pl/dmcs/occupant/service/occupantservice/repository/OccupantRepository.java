package pl.dmcs.occupant.service.occupantservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmcs.occupant.service.occupantservice.model.Occupant;

public interface OccupantRepository extends JpaRepository<Occupant,Integer> {
}
