package pl.dmcs.manager.service.managerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dmcs.manager.service.managerservice.model.Premises;

import java.util.Set;

@Repository
public interface PremisesRepository extends JpaRepository<Premises,Integer> {
    @Query("SELECT u from Premises u where occupant_id is null")
    Set<Premises> getAllAvailablePremises();
}
