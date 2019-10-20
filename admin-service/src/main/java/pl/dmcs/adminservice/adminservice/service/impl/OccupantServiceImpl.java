package pl.dmcs.adminservice.adminservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.adminservice.adminservice.exception.OccupantNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Occupant;
import pl.dmcs.adminservice.adminservice.repository.OccupantRepository;
import pl.dmcs.adminservice.adminservice.service.inf.OccupantService;

@Service
public class OccupantServiceImpl implements OccupantService {

    @Autowired
    private OccupantRepository occupantRepository;

    @Override
    public Occupant get(int id) throws OccupantNotFoundException {
        return occupantRepository.findById(id).orElseThrow(() -> new OccupantNotFoundException("Occupant id:" + id));
    }


}
