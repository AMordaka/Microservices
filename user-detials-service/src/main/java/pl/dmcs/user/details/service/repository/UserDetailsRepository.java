package pl.dmcs.user.details.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmcs.user.details.service.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    UserDetails findByUserId(int id);
}
