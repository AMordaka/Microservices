package pl.dmcs.user.details.service.service;

import pl.dmcs.user.details.service.model.UserDetails;

public interface UserDetailsService {

    UserDetails getUserDetails(Integer id);

    void addUserDetails(UserDetails userDetails);
}
