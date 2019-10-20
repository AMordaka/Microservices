package pl.dmcs.adminservice.adminservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.adminservice.adminservice.model.Premises;

@Repository
public interface PremisesRepository extends JpaRepository<Premises,Integer> {

}
