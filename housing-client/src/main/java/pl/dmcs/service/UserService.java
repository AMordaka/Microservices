package pl.dmcs.service;

import pl.dmcs.model.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    User findByLogin(String login);

    void registerUser(User user);

    void updateUser(User user);

    List<User> getAllUsers();

    List<User> getActivatedUsers();

    void assignUserToLocal(Integer userId, Integer localId);

    void removeUser(Integer userId);

}
