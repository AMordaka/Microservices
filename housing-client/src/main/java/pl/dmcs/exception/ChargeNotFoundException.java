package pl.dmcs.exception;

import pl.dmcs.model.User;

public class ChargeNotFoundException extends AbstractNotFoundException {

    public ChargeNotFoundException(String code) {
        super(User.class, code);
    }
}
