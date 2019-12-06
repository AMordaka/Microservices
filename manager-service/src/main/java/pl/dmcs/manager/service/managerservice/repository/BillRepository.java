package pl.dmcs.manager.service.managerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.manager.service.managerservice.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

}
