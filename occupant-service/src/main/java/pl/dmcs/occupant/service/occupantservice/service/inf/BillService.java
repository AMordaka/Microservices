package pl.dmcs.occupant.service.occupantservice.service.inf;


import pl.dmcs.occupant.service.occupantservice.exception.BillNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Bill;

public interface BillService {
    Bill get(int id) throws BillNotFoundException;
    void addBillToPremises(Bill bill, int premisesId);
    byte[] getPdfForBillId(int id, String lang);
}
