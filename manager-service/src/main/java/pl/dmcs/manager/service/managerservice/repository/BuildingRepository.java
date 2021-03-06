package pl.dmcs.manager.service.managerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dmcs.manager.service.managerservice.model.Building;

import java.util.Set;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {
    @Query("SELECT u from Building u where manager_id is null")
    Set<Building> getAllAvailableBuildings();
}
