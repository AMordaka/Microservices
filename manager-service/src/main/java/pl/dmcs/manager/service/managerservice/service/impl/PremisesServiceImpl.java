package pl.dmcs.manager.service.managerservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.manager.service.managerservice.exception.BillNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.BuildingNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Bill;
import pl.dmcs.manager.service.managerservice.model.Building;
import pl.dmcs.manager.service.managerservice.model.Occupant;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.repository.PremisesRepository;
import pl.dmcs.manager.service.managerservice.service.impl.converter.PremisesBillConverter;
import pl.dmcs.manager.service.managerservice.service.inf.BillService;
import pl.dmcs.manager.service.managerservice.service.inf.BuildingService;
import pl.dmcs.manager.service.managerservice.service.inf.OccupantService;
import pl.dmcs.manager.service.managerservice.service.inf.PremisesService;

import java.util.List;
import java.util.Set;

@Service
public class PremisesServiceImpl implements PremisesService {

    @Autowired
    private PremisesRepository premisesRepository;

    @Autowired
    private OccupantService occupantService;

    @Autowired
    private BillService billService;

    @Autowired
    private BuildingService buildingService;


    @Override
    public int save(Premises premises) {
        return premisesRepository.save(premises).getId();
    }

    @Override
    public Premises get(int id) throws PremisesNotFoundException {
        return premisesRepository.findById(id).orElseThrow(()->new PremisesNotFoundException("Premises id:" + id));
    }

    @Override
    public int update(Premises premises) {
        return premisesRepository.saveAndFlush(premises).getId();
    }

    @Override
    public void delete(int id) {
        premisesRepository.deleteById(id);
    }

    @Override
    public void addBillToPremises(Bill bill, int premisesId) {
        Premises premises = null;

        try {
            premises = get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        if (premises != null) {
            List<Bill> bills =  premises.getBills();

            Bill convertedBill = PremisesBillConverter.convert(bill);

            bills.add(convertedBill);
            convertedBill.setPremises(premises);
            premises.setBills(bills);
            billService.save(convertedBill);
            premisesRepository.save(premises);
        }
    }

    @Override
    public void deleteBillFromPremises(int billId, int premisesId) {
        Premises premises = null;

        try {
            premises = get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        List<Bill> bills = premises.getBills();

        bills.removeIf(bill -> bill.getId() == billId);
        premises.setBills(bills);
        premisesRepository.save(premises);
    }

    @Override
    public Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException {
        Occupant occupant = occupantService.get(occupantId);
        return occupant.getPremises();
    }

    @Override
    public void setBillAccepted(int billId) {
        Bill bill = null;
        try {
            bill = billService.get(billId);
        } catch (BillNotFoundException e) {
            e.printStackTrace();
        }
        bill.setAccepted(true);
        billService.save(bill);
    }

    @Override
    public Set<Premises> getPremisesForSpecificBuidling(int buildingId) throws BuildingNotFoundException {
        Building building = buildingService.get(buildingId);
        return building.getPremises();
    }

    @Override
    public Set<Premises> getAllAvailablePremises() {
        return premisesRepository.getAllAvailablePremises();
    }

    @Override
    public List<Premises> getAllPremises() {
        return premisesRepository.findAll();
    }
}
