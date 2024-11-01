package com.kama.springboot.service;

import com.kama.springboot.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles ();
    void addRole(Role role);
    Role findById(int id);
    Set<Role> findByIdRoles(List<Long>roles);
}
