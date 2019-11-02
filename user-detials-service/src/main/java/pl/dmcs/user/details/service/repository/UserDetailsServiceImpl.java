package pl.dmcs.user.details.service.repository;

import pl.dmcs.user.details.service.service.UserDetailsService;
import pl.dmcs.user.details.service.model.UserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails getUserDetails(Integer id) {
        return userDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public void addUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }
}
