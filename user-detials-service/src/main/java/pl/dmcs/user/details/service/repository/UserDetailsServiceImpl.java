package pl.dmcs.user.details.service.repository;

import org.springframework.stereotype.Service;
import pl.dmcs.user.details.service.service.UserDetailsService;
import pl.dmcs.user.details.service.model.UserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails getUserDetails(Integer id) {
        UserDetails userDetails = userDetailsRepository.findByUserId(id);
        if (userDetails == null) {
            throw new RuntimeException("Not found");
        }
        return userDetails;
    }

    @Override
    public void addUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }
}
