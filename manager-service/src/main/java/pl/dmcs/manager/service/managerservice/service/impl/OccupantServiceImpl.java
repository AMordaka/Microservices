package pl.dmcs.manager.service.managerservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dmcs.manager.service.managerservice.exception.OccupantNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.PremisesNotFoundException;
import pl.dmcs.manager.service.managerservice.exception.UserDetailsNotFoundException;
import pl.dmcs.manager.service.managerservice.model.Occupant;
import pl.dmcs.manager.service.managerservice.model.Premises;
import pl.dmcs.manager.service.managerservice.model.Role;
import pl.dmcs.manager.service.managerservice.model.User;
import pl.dmcs.manager.service.managerservice.model.dto.OccupantDetailsDto;
import pl.dmcs.manager.service.managerservice.model.dto.OccupantDto;
import pl.dmcs.manager.service.managerservice.model.dto.UpdateOccupantDto;
import pl.dmcs.manager.service.managerservice.model.dto.UserDetails;
import pl.dmcs.manager.service.managerservice.repository.OccupantRepository;
import pl.dmcs.manager.service.managerservice.service.inf.OccupantService;
import pl.dmcs.manager.service.managerservice.service.inf.PremisesService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OccupantServiceImpl implements OccupantService {

    @Autowired
    private OccupantRepository occupantRepository;

    @Autowired
    private PremisesService premisesService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int save(OccupantDto occupantDto) {
        Occupant occupant = new Occupant();
        User user = new User();
        user.setName(occupantDto.getFirstName());
        user.setLastName(occupantDto.getLastName());
        user.setEmail(occupantDto.getEmail());
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("ROLE_OCCUPANT");
        roles.add(role);
        user.setRoles(roles);
        user.setActive(occupantDto.getActive());
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        occupant.setUser(user);
        return occupantRepository.saveAndFlush(occupant).getId();
    }

    @Override
    public Occupant get(int id) throws OccupantNotFoundException {
        return occupantRepository.findById(id).orElseThrow(() -> new OccupantNotFoundException("Occupant id:" + id));
    }

    @Override
    public int update(UpdateOccupantDto occupantDto) throws OccupantNotFoundException {
        Occupant occupant = get(occupantDto.getId());
        User user = occupant.getUser();
        user.setName(occupantDto.getFirstName());
        user.setLastName(occupantDto.getLastName());
        user.setActive(occupantDto.getActive());
        user.setEmail(occupantDto.getEmail());
        return occupantRepository.saveAndFlush(occupant).getId();
    }

    @Override
    public void delete(int id) throws OccupantNotFoundException {
        Occupant occupant = null;
        try {
            occupant = get(id);
        } catch (OccupantNotFoundException e) {
            throw new OccupantNotFoundException("User id" + id);
        }

        Set<Premises> premisesList = occupant.getPremises();

        for (Premises premises : premisesList) {
            premises.setOccupant(null);
        }
        occupant.setPremises(premisesList);
        occupantRepository.save(occupant);
        occupantRepository.deleteById(id);
    }

    @Override
    public void addPremisesToOccupant(int premisesId, int occupantId) {
        Occupant occupant = null;
        Premises premises = null;

        try {
            occupant = get(occupantId);
        } catch (OccupantNotFoundException e) {
            e.printStackTrace();
        }

        try {
            premises = premisesService.get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        if (occupant != null && premises !=null && premises.getOccupant() == null) {
            Set<Premises> premisesSet = occupant.getPremises();
            premisesSet.add(premises);
            premises.setOccupant(occupant);
            occupant.setPremises(premisesSet);
            occupantRepository.save(occupant);
        }
    }

    @Override
    public void deletePremisesFromOccupant(int premisesId, int occupantId) {
        Occupant occupant = null;

        try {
            occupant = get(occupantId);
        } catch (OccupantNotFoundException e) {
            e.printStackTrace();
        }

        Set<Premises> premisesSet = occupant.getPremises();

        premisesSet.removeIf(premises -> premises.getId() == premisesId);

        occupant.setPremises(premisesSet);
        occupantRepository.save(occupant);
    }

    @Override
    public OccupantDetailsDto getWithDetails(int id, String token) throws OccupantNotFoundException, UserDetailsNotFoundException {
        Occupant occupant = get(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",token);
        HttpEntity entity = new HttpEntity(httpHeaders);
        UserDetails userDetails = restTemplate.exchange("http://localhost:8093/user-details-service/" +id,HttpMethod.GET,entity,UserDetails.class).getBody();

        if (userDetails == null) {
            throw new UserDetailsNotFoundException("User details not found");
        }
        OccupantDetailsDto occupantDetailsDto = new OccupantDetailsDto();
        occupantDetailsDto.setFirstName(occupant.getUser().getName());
        occupantDetailsDto.setLastName(occupant.getUser().getLastName());
        occupantDetailsDto.setEmail(occupant.getUser().getEmail());
        occupantDetailsDto.setActive(occupant.getUser().getActive());
        occupantDetailsDto.setAccountNumber(userDetails.getAccountNumber());
        occupantDetailsDto.setPesel(userDetails.getPesel());


        return occupantDetailsDto;
    }

    @Override
    public List<Occupant> getAll() {
        return occupantRepository.findAll();
    }

    @Override
    public void addUserDetailsToOccupant(UserDetails userDetails) throws OccupantNotFoundException {
        restTemplate.postForEntity("http://localhost:8093/user-details-service",userDetails,UserDetails.class);
    }
}
