package com.kama.springboot.dao;

import com.kama.springboot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void removeUser(int id) {
        em.remove(getUserById(id));
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserByUsername(String name) {
        TypedQuery<User> q = (em.createQuery("select u from User u " +
                "join fetch u.roles where u.name = :name",User.class));
        q.setParameter("name",name);
        return q.getResultList().stream().findFirst().orElse(null);
    }
}
