package pl.dmcs.adminservice.adminservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.adminservice.adminservice.exception.PremisesNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Premises;
import pl.dmcs.adminservice.adminservice.repository.PremisesRepository;
import pl.dmcs.adminservice.adminservice.service.inf.PremisesService;

@Service
public class PremisesServiceImpl implements PremisesService {

    @Autowired
    private PremisesRepository premisesRepository;

    @Override
    public Premises get(int id) throws PremisesNotFoundException {
        return premisesRepository.findById(id).orElseThrow(()->new PremisesNotFoundException("Premises id:" + id));
    }


}
