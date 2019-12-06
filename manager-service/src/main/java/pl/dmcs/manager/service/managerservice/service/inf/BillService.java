package pl.dmcs.manager.service.managerservice.service.inf;

import pl.dmcs.manager.service.managerservice.exception.BillNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Bill;

import java.util.List;

public interface BillService {
    Bill get(int id) throws BillNotFoundException;
    void save(Bill bill);
    List<Bill> getBillsForSpecificPremises(int id) throws PremisesNotFoundException;
    void addBillToPremises(Bill bill, int premisesId);
    void deleteBillFromPremises(int billId, int premisesId);
    byte[] getPdfForBillId(int id,String lang);
}
