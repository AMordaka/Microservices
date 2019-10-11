package pl.dmcs.exception;

import pl.dmcs.model.User;

public class TokenNotFoundException extends AbstractNotFoundException {

    public TokenNotFoundException(String code) {
        super(User.class, code);
    }
}
