package pl.dmcs.controller;

import org.springframework.web.bind.annotation.*;
import pl.dmcs.model.User;
import pl.dmcs.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String registerUser(@Valid User user) {
        userService.registerUser(user);
        return "USER REGISTERED";
    }
    @GetMapping(value = "/all")
    public List<User> listUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "all/activated")
    public List<User> listActiveUsers(){
        return userService.getActivatedUsers();
    }

    @DeleteMapping(value = "{id}")
    public void removeUser(@PathVariable Integer userId){
        userService.removeUser(userId);
    }

    @PutMapping
    public String updateUser(@Valid User user) {
        userService.updateUser(user);
        return "UPDATED USER";
    }

    @PutMapping(value = "/{userId}/{localId}")
    public String assignUserToLocal(@PathVariable Integer userId, @PathVariable Integer localId) {
        userService.assignUserToLocal(userId,localId);
        return "ASSIGNED USER:" +userId + " TO LOCAL:" + localId;
    }
}
