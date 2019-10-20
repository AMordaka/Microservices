package pl.dmcs.adminservice.adminservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.adminservice.adminservice.exception.BillNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Bill;
import pl.dmcs.adminservice.adminservice.repository.BillRepository;
import pl.dmcs.adminservice.adminservice.service.inf.BillService;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill get(int id) throws BillNotFoundException {
        return billRepository.findById(id).orElseThrow( () -> new BillNotFoundException("Bill id:" + id));
    }

}
