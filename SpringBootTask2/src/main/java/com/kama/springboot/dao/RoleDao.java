package com.kama.springboot.dao;

import com.kama.springboot.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAllRoles ();
    void addRole(Role role);
    Role findById(int id);
    Set<Role> findByIdRoles(List<Long>roles);
}
