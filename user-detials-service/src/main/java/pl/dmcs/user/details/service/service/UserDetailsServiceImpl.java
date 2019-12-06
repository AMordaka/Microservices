package pl.dmcs.user.details.service.service;

import org.springframework.stereotype.Service;
import pl.dmcs.user.details.service.exception.UserDetailsAlreadyExistException;
import pl.dmcs.user.details.service.exception.UserDetailsNotFoundException;
import pl.dmcs.user.details.service.model.dto.AccountNumberDto;
import pl.dmcs.user.details.service.repository.UserDetailsRepository;
import pl.dmcs.user.details.service.service.UserDetailsService;
import pl.dmcs.user.details.service.model.UserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails getUserDetails(Integer id) throws UserDetailsNotFoundException {
        UserDetails userDetails = userDetailsRepository.findByUserId(id);
        if (userDetails == null) {
            throw new UserDetailsNotFoundException("Not found: " +id);
        }
        return userDetails;
    }

    @Override
    public void addUserDetails(UserDetails userDetails) throws UserDetailsAlreadyExistException, UserDetailsNotFoundException {
        UserDetails ud = userDetailsRepository.findByUserId(userDetails.getUserId());
        if (ud != null) {
            throw new UserDetailsAlreadyExistException();
        }
        userDetailsRepository.save(userDetails);
    }

    @Override
    public void updateAccountNumber(Integer id, AccountNumberDto accountNumberDto) throws UserDetailsNotFoundException {
        UserDetails userDetails = userDetailsRepository.findByUserId(id);
        if (userDetails == null) {
            throw new UserDetailsNotFoundException("Not found: " +id);
        }
        userDetails.setAccountNumber(accountNumberDto.getAccountNumber());
        userDetailsRepository.save(userDetails);
    }
}
