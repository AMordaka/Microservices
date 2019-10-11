package pl.dmcs.exception;

import pl.dmcs.model.User;

public class LocalNotFoundException extends AbstractNotFoundException {

    public LocalNotFoundException(String code) {
        super(User.class, code);
    }
}
