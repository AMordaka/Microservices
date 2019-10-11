package pl.dmcs.exception;

import pl.dmcs.model.User;

public class UserNotFoundException extends AbstractNotFoundException {

    public UserNotFoundException(String code) {
        super(User.class, code);
    }
}
