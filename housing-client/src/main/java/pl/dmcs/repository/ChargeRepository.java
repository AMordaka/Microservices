package pl.dmcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.model.Charge;
import pl.dmcs.model.Local;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Integer> {

    Optional<List<Charge>> getAllByLocal(Local local);
}
