package pl.dmcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.model.Local;
import pl.dmcs.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {

    Optional<List<Local>> findLocalsByUser(User user);
}
