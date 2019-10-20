package pl.dmcs.adminservice.adminservice.service.inf;


import pl.dmcs.adminservice.adminservice.exception.BillNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Bill;

public interface BillService {
    Bill get(int id) throws BillNotFoundException;

}
