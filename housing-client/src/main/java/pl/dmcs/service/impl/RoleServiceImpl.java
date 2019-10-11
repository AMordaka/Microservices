package pl.dmcs.service.impl;

import org.springframework.stereotype.Service;
import pl.dmcs.exception.RoleNotFoundException;
import pl.dmcs.model.Role;
import pl.dmcs.repository.RoleRepository;
import pl.dmcs.service.RoleService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role findById(Integer roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(String.valueOf(roleId)));
    }
}
