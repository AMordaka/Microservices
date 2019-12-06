package pl.dmcs.occupant.service.occupantservice.service.inf;


import pl.dmcs.occupant.service.occupantservice.exception.BillNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Bill;
import pl.dmcs.occupant.service.occupantservice.model.dto.BillDto;

public interface BillService {
    Bill get(int id) throws BillNotFoundException;
    void addBillToPremises(BillDto bill, int premisesId);
    byte[] getPdfForBillId(int id, String lang);
}
