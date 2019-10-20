package pl.dmcs.occupant.service.occupantservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.occupant.service.occupantservice.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

}
