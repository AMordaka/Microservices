package pl.dmcs.manager.service.managerservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.manager.service.managerservice.exception.BillNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Bill;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.repository.BillRepository;
import pl.dmcs.manager.service.managerservice.service.inf.BillService;
import pl.dmcs.manager.service.managerservice.service.inf.PremisesService;

import java.util.Collections;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private PremisesService premisesService;

    @Autowired
    private BillRepository billRepository;


    @Override
    public Bill get(int id) throws BillNotFoundException {
        return billRepository.findById(id).orElseThrow( () -> new BillNotFoundException("Bill id:" + id));
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public List<Bill> getBillsForSpecificPremises(int id) throws PremisesNotFoundException {
        Premises premises = premisesService.get(id);
        List<Bill> bills = premises.getBills();
        Collections.sort(bills);
        return bills;
    }

}
