package pl.dmcs.manager.service.managerservice.service.impl;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.manager.service.managerservice.exception.BillNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Bill;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.repository.BillRepository;
import pl.dmcs.manager.service.managerservice.service.impl.converter.PremisesBillConverter;
import pl.dmcs.manager.service.managerservice.service.impl.generator.PdfGenerator;
import pl.dmcs.manager.service.managerservice.service.inf.BillService;
import pl.dmcs.manager.service.managerservice.service.inf.PremisesService;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    @Override
    public void addBillToPremises(Bill bill, int premisesId) {
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
    public void deleteBillFromPremises(int billId, int premisesId) {
        Premises premises = null;

        try {
            premises = premisesService.get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        List<Bill> bills = premises.getBills();

        bills.removeIf(bill -> bill.getId() == billId);
        premises.setBills(bills);
        premisesService.save(premises);
    }

    @Override
    public byte[] getPdfForBillId(int id, String lang) {
        Bill bill = null;
        try {
            bill = get(id);
        } catch (BillNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return PdfGenerator.generate(bill,lang);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
