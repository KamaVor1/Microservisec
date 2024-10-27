package com.kama.springboot.controller;

import com.kama.springboot.model.User;
import com.kama.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("Users", userService.getAllUsers());
        return "pages/Users";
    }

    @GetMapping("/addNewUser")
    public String addUser(User user) {
        return "pages/addUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if(user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @RequestMapping("/removeUser/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @RequestMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        User user = (User) userService.getUserById(id);
        model.addAttribute("user", user);
        return "pages/addUser";
    }
}
