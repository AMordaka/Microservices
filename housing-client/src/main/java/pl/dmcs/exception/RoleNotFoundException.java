package pl.dmcs.exception;

import pl.dmcs.model.User;

public class RoleNotFoundException extends AbstractNotFoundException {

    public RoleNotFoundException(String code) {
        super(User.class, code);
    }
}
