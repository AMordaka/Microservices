package pl.dmcs.occupant.service.occupantservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.occupant.service.occupantservice.exception.OccupantNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Occupant;
import pl.dmcs.occupant.service.occupantservice.repository.OccupantRepository;
import pl.dmcs.occupant.service.occupantservice.service.inf.OccupantService;

@Service
public class OccupantServiceImpl implements OccupantService {

    @Autowired
    private OccupantRepository occupantRepository;

    @Override
    public Occupant get(int id) throws OccupantNotFoundException {
        return occupantRepository.findById(id).orElseThrow(() -> new OccupantNotFoundException("Occupant id:" + id));
    }


}
