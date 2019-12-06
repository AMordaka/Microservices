package pl.dmcs.adminservice.adminservice.service.inf;


import pl.dmcs.adminservice.adminservice.exception.PremisesNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Premises;

public interface PremisesService {
    Premises get(int id) throws PremisesNotFoundException;
}
