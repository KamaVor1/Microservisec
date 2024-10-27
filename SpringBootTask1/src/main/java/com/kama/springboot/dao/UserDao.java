package com.kama.springboot.dao;



import com.kama.springboot.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User getUserById(int id);
    void updateUser(User user);
    void removeUser(int id);
    void addUser(User user);
}
