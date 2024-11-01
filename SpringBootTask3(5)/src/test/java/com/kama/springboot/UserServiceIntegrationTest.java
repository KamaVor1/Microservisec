package com.kama.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.kama.springboot.service.UserService;
import com.kama.springboot.dao.UserDao;
import com.kama.springboot.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setName("testuser");
        testUser.setPassword("password");
        userService.addUser(testUser);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testGetUserById() {
        User user = userService.getUserById(testUser.getId());
        assertNotNull(user);
        assertEquals("testuser", user.getUsername()); // используем getUsername()
    }

    @Test
    public void testAddUser() {
        User newUser = new User();
        newUser.setName("newuser");
        newUser.setPassword("newpassword");

        userService.addUser(newUser);

        User retrievedUser = userService.getUserByUsername("newuser");
        assertNotNull(retrievedUser);
        assertEquals("newuser", retrievedUser.getUsername()); // используем getUsername()
    }

    @Test
    public void testRemoveUser() {
        userService.removeUser(testUser.getId());

        User deletedUser = userService.getUserById(testUser.getId());
        assertNull(deletedUser);
    }

    @Test
    public void testUpdateUser() {
        testUser.setPassword("updatedpassword");
        userService.updateUser(testUser);

        User updatedUser = userService.getUserById(testUser.getId());
        assertNotNull(updatedUser);
        assertEquals("updatedpassword", updatedUser.getPassword());
    }

    @Test
    public void testGetUserByUsername() {
        User user = userService.getUserByUsername("testuser");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername()); // используем getUsername()
    }
}