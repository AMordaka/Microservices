package pl.dmcs.user.details.service.service;

import pl.dmcs.user.details.service.exception.UserDetailsNotFoundException;
import pl.dmcs.user.details.service.model.UserDetails;
import pl.dmcs.user.details.service.model.dto.AccountNumberDto;

public interface UserDetailsService {

    UserDetails getUserDetails(Integer id) throws UserDetailsNotFoundException;

    void addUserDetails(UserDetails userDetails);

    void updateAccountNumber(Integer id, AccountNumberDto accountNumberDto) throws UserDetailsNotFoundException;
}
