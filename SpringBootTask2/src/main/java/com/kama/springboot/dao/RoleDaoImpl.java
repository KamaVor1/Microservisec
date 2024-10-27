package com.kama.springboot.dao;

import com.kama.springboot.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public Role findById(int id) {
        return em.find(Role.class, id);
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        TypedQuery<Role> q = em.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role",roles);
        return new HashSet<>(q.getResultList());
    }
}
