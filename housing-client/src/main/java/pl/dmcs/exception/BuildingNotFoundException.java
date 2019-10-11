package pl.dmcs.exception;

import pl.dmcs.model.Building;

public class BuildingNotFoundException extends AbstractNotFoundException {

    public BuildingNotFoundException(String code) {
        super(Building.class, code);
    }
}
