package pl.dmcs.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.exception.ChargeNotFoundException;
import pl.dmcs.exception.LocalNotFoundException;
import pl.dmcs.exception.UserNotFoundException;
import pl.dmcs.model.Charge;
import pl.dmcs.model.Local;
import pl.dmcs.repository.ChargeRepository;
import pl.dmcs.repository.LocalRepository;
import pl.dmcs.repository.UserRepository;
import pl.dmcs.service.ChargeService;
import pl.dmcs.service.LocalService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocalServiceImpl implements LocalService {

    private final LocalRepository localRepository;
    private final UserRepository userRepository;
    private final ChargeRepository chargeRepository;
    private final ChargeService chargeService;

    public LocalServiceImpl(LocalRepository localRepository, UserRepository userRepository, ChargeRepository chargeRepository, ChargeService chargeService) {
        this.localRepository = localRepository;
        this.userRepository = userRepository;
        this.chargeRepository = chargeRepository;
        this.chargeService = chargeService;
    }

    @Override
    public List<Local> getAllLocals() {
        return localRepository.findAll();
    }

    @Override
    public void registerLocal(Local local) {
        localRepository.save(local);
    }

    @Override
    public List<Local> getNotRentedLocals() {
        return localRepository.findAll().stream().filter(local -> !local.getIsRented()).collect(Collectors.toList());
    }

    @Override
    public List<Local> getUserLocals(String userId) {
        return localRepository.findLocalsByUser(userRepository.findByLogin(userId).orElseThrow(() -> new UserNotFoundException(userId))).orElseThrow(() -> new LocalNotFoundException(""));
    }

    @Override
    public void fillLocalCharge(Integer localId, Charge charge) {
        Local local = localRepository.findById(localId).orElseThrow(() -> new LocalNotFoundException(String.valueOf(localId)));
        local.setIsChargesFilled(true);
        local.setCanFillCharges(false);
        local.getCharges().add(charge);
        charge.setLocal(local);
        chargeRepository.save(charge);
        localRepository.save(local);
    }

    @Override
    public Charge findLatestChargeFromLocal(Integer localId) {
        Local local = localRepository.findById(localId).orElseThrow(() -> new LocalNotFoundException(String.valueOf(localId)));
        List<Charge> charges = new ArrayList<>(local.getCharges());
        return chargeRepository.findById(charges.get(charges.size() - 1).getId()).orElseThrow(() -> new ChargeNotFoundException(String.valueOf(localId)));
    }

    @Override
    public void confirmCharges(Integer localId, Charge charge) {
        Local local = localRepository.findById(localId).orElseThrow(() -> new LocalNotFoundException(String.valueOf(localId)));
        local.setIsChargesAccepted(true);
        local.setIsChargesFilled(false);
        localRepository.save(local);
    }

    @Override
    public Local getLocalById(Integer localId) {
        return localRepository.findById(localId).orElseThrow(() -> new LocalNotFoundException(String.valueOf(localId)));
    }

    @Override
    @Scheduled(cron = "0 0 0 L * ? *")
    public void generateAmounts() {
        List<Local> locals = localRepository.findAll();
        for (Local local : locals) {
            if (!local.getIsChargesAccepted()) {
                Charge charge;
                if (local.getIsHousing()) {
                    charge = getDefaultChargeForHousing(local);
                } else {
                    charge = getDefaultChargeForNotHousing(local);
                }
                local.setIsChargesFilled(true);
                local.setCanFillCharges(false);
                local.getCharges().add(charge);
                chargeRepository.save(charge);
                localRepository.save(local);
            }
        }
    }

    @Override
    public void acceptAllCharges() {
        List<Local> locals = localRepository.findAll();
        for (Local local : locals) {
            local.setIsChargesAccepted(true);
            local.setIsChargesFilled(false);
            localRepository.save(local);
        }
    }

    @Override
    public void update(Local local) {
        registerLocal(local);
    }

    @Override
    public void remove(Integer id) {
        localRepository.deleteById(id);
    }

    Charge getDefaultChargeForHousing(Local local) {
        Double defaultDouble = Double.parseDouble("10");
        return new Charge(LocalDate.now(), defaultDouble, defaultDouble, defaultDouble, defaultDouble, defaultDouble, defaultDouble, local);
    }

    Charge getDefaultChargeForNotHousing(Local local) {
        Double defaultDouble = Double.parseDouble("20");
        return new Charge(LocalDate.now(), defaultDouble, defaultDouble, defaultDouble, defaultDouble, defaultDouble, defaultDouble, local);
    }
}
