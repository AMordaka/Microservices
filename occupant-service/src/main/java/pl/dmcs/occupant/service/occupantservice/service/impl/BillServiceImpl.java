package pl.dmcs.occupant.service.occupantservice.service.impl;


import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.occupant.service.occupantservice.exception.BillNotFoundException;
import pl.dmcs.occupant.service.occupantservice.exception.PremisesNotFoundException;
import pl.dmcs.occupant.service.occupantservice.model.Bill;
import pl.dmcs.occupant.service.occupantservice.model.Premises;
import pl.dmcs.occupant.service.occupantservice.model.dto.BillDto;
import pl.dmcs.occupant.service.occupantservice.repository.BillRepository;
import pl.dmcs.occupant.service.occupantservice.service.impl.converter.PremisesBillConverter;
import pl.dmcs.occupant.service.occupantservice.service.impl.generator.PdfGenerator;
import pl.dmcs.occupant.service.occupantservice.service.inf.BillService;
import pl.dmcs.occupant.service.occupantservice.service.inf.PremisesService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private PremisesService premisesService;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillService billService;


    @Override
    public Bill get(int id) throws BillNotFoundException {
        return billRepository.findById(id).orElseThrow( () -> new BillNotFoundException("Bill id:" + id));
    }

    @Override
    public void addBillToPremises(BillDto bill, int premisesId) {
        Premises premises = null;

        try {
            premises = premisesService.get(premisesId);
        }  catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        if (premises != null) {
            List<Bill> bills =  premises.getBills();

            Bill convertedBill = PremisesBillConverter.convert(bill);

            bills.add(convertedBill);
            convertedBill.setPremises(premises);
            premises.setBills(bills);
            billRepository.save(convertedBill);
            premisesService.save(premises);
        }
    }



    @Override
    public byte[] getPdfForBillId(int id, String lang) {
        Bill bill = null;
        try {
            bill = billService.get(id);
        } catch (BillNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return PdfGenerator.generate(bill,lang);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
