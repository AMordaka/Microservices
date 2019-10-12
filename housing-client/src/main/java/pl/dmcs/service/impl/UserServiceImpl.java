package pl.dmcs.service.impl;

import org.springframework.stereotype.Service;
import pl.dmcs.exception.LocalNotFoundException;
import pl.dmcs.exception.UserNotFoundException;
import pl.dmcs.model.DTO.UserLocalDTO;
import pl.dmcs.model.Local;
import pl.dmcs.model.User;
import pl.dmcs.repository.LocalRepository;
import pl.dmcs.repository.UserRepository;
import pl.dmcs.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final String REGISTERED_USER = "Rejestracja użytkownika";

    private final UserRepository userRepository;
    private final LocalRepository localRepository;

    public UserServiceImpl(UserRepository userRepository, LocalRepository localRepository) {
        this.userRepository = userRepository;
        this.localRepository = localRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
    }

    @Override
    public void registerUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getActivatedUsers() {
        return userRepository.findAll().stream().filter(User::getIsActive).collect(Collectors.toList());
    }

    @Override
    public void assignUserToLocal(Integer userId, Integer localId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));
        Local local = localRepository.findById(localId).orElseThrow(() -> new LocalNotFoundException(String.valueOf(localId)));
        local.setUser(user);
        local.setIsRented(true);
        localRepository.save(local);
    }

    @Override
    public void removeUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
