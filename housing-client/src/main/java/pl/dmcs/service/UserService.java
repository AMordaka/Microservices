package pl.dmcs.service;

import pl.dmcs.model.ConfirmationToken;
import pl.dmcs.model.DTO.UserLocalDTO;
import pl.dmcs.model.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    User findByLogin(String login);

    void registerUser(User user);

    List<User> getAllUsers();

    List<User> getActivatedUsers();

    void assignUserToLocal(UserLocalDTO dto);

}
