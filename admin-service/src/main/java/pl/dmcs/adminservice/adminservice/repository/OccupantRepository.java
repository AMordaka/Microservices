package pl.dmcs.adminservice.adminservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.adminservice.adminservice.model.Occupant;

@Repository
public interface OccupantRepository extends JpaRepository<Occupant,Integer> {
}
