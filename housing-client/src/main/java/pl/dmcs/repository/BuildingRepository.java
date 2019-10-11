package pl.dmcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.model.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
