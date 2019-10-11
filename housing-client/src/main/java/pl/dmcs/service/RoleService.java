package pl.dmcs.service;

import pl.dmcs.model.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> findAll();

    Role findById(Integer roleId);
}
